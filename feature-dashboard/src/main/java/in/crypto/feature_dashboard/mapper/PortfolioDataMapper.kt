package `in`.crypto.feature_dashboard.mapper

import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.Position
import `in`.crypto.core_model.Transaction
import `in`.crypto.core_model.coin.CoinId
import `in`.crypto.core_model.helper.PositionGenerator.buildPosition
import `in`.crypto.core_model.market.PortfolioData
import `in`.crypto.core_model.portfolio.PortfolioMetrics
import `in`.crypto.feature_dashboard.state.DashboardUiState
import `in`.crypto.shared_ui.mappers.toCoinCardUiModel
import `in`.crypto.shared_ui.mappers.toPortfolioUiModel
import `in`.crypto.shared_ui.mappers.toTransactionListItemUiModel
import `in`.crypto.shared_ui.models.CoinCardUiModel
import `in`.crypto.shared_ui.models.TransactionListItemUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import java.math.BigDecimal
import java.math.RoundingMode

fun PortfolioData.toDashboardUiState(
    positions: PersistentMap<CoinId, Position>,
    coinUiModels: PersistentMap<CoinId, CoinCardUiModel>
): DashboardUiState {

    val positions = positions.values.toPersistentList()
    val portfolioUiModel = positions.calculatePortfolioMetrics().toPortfolioUiModel()
    val coinCards = coinUiModels.values.toPersistentList()
    val transactionItems = transactions.toTransactionUiModels()

    return DashboardUiState(
        isLoading = false,
        isMarketOpen = true,
        portfolioUiModel = portfolioUiModel,
        holdings = coinCards,
        transactions = transactionItems,
    )
}

fun PortfolioData.calculatePositions(): PersistentList<Position> {

    return holdings.map { holding ->
        buildPosition(
            holding = holding,
            coin = coins.getValue(holding.coinId)
        )
    }.toPersistentList()
}

fun PortfolioData.calculatePositions(
    previousPositions: PersistentMap<CoinId, Position>
): PersistentMap<CoinId, Position> {

    var updatedPositions = persistentMapOf<CoinId, Position>()

    holdings.forEach { holding ->

        val previousPosition = previousPositions[holding.coinId]
        val coin = coins.getValue(holding.coinId)

        val position = if (previousPosition != null && previousPosition.coin === coin) {
            previousPosition
        } else {
            buildPosition(
                holding = holding,
                coin = coin
            )
        }

        updatedPositions = updatedPositions.put(holding.coinId, position)
    }
    return updatedPositions
}

fun PersistentList<Position>.calculatePortfolioMetrics(): PortfolioMetrics {

    var investedAmount = Money(BigDecimal.ZERO, CurrencyCode.USD)
    var currentValue = Money(BigDecimal.ZERO, CurrencyCode.USD)

    forEach { position ->
        investedAmount += position.investedAmount
        currentValue += position.currentValue
    }

    val pnl = currentValue - investedAmount
    val pnlPercentage = if (investedAmount.amount.compareTo(BigDecimal.ZERO) == 0) {
        0f
    } else {
        (pnl.amount / investedAmount.amount.setScale(2, RoundingMode.HALF_UP)).toFloat() * 100
    }

    return PortfolioMetrics(
        investedAmount = investedAmount,
        currentValue = currentValue,
        pnl = pnl,
        pnlPercentage = pnlPercentage
    )
}

fun PersistentList<Position>.toCoinUiModels(): PersistentList<CoinCardUiModel> {
    return map { it.toCoinCardUiModel() }.toPersistentList()
}

fun PersistentMap<CoinId, Position>.toCoinUiModels(
    previousPositions: PersistentMap<CoinId, Position>,
    previousCoinCards: PersistentMap<CoinId, CoinCardUiModel>
): PersistentMap<CoinId, CoinCardUiModel> =
    mapValues { (coinId, position) ->
        val previousPosition = previousPositions[coinId]
        val previousUiModel = previousCoinCards[coinId]

        if (
            previousPosition === position &&
            previousUiModel != null
        ) {
            previousUiModel
        } else {
            position.toCoinCardUiModel()
        }
    }.toPersistentMap()

fun PersistentList<Position>.toCoinUiModels(
    previousPositions: PersistentMap<CoinId, Position>?,
    previousModels : PersistentMap<CoinId, CoinCardUiModel>?
) : PersistentMap<CoinId, CoinCardUiModel> {

    var updatedModels = persistentMapOf<CoinId, CoinCardUiModel>()

    forEach { position ->
        val coinId: CoinId = position.coin.id
        val model = if (
            previousPositions != null &&
            previousModels != null &&
            position === previousPositions[coinId]
        ) {
            previousModels.getValue(coinId)
        } else {
            position.toCoinCardUiModel()
        }

        updatedModels.put(coinId, model)
    }
    return updatedModels
}

fun PersistentList<Transaction>.toTransactionUiModels(): PersistentList<TransactionListItemUiModel> {
    return map { it.toTransactionListItemUiModel() }.toPersistentList()
}