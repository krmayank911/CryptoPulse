package `in`.crypto.feature_dashboard.`source-fake`

import `in`.crypto.core_model.helper.PositionGenerator.buildPosition
import `in`.crypto.fake.portfolio.getMyPortfolio
import `in`.crypto.fake.coins.getCoins
import `in`.crypto.fake.coins.updateMarket
import `in`.crypto.fake.holdings.getMyHoldings
import `in`.crypto.fake.transactions.transactions
import `in`.crypto.feature_dashboard.state.DashboardUiState
import `in`.crypto.shared_ui.mappers.toCoinCardUiModel
import `in`.crypto.shared_ui.mappers.toPortfolioUiModel
import `in`.crypto.shared_ui.mappers.toTransactionListItemUiModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class FakeDashboardFeed: DashboardFeed {
    override fun observe(): Flow<DashboardUiState> {
        return flow {

            var market = getCoins().associateBy { it.id }

            while (currentCoroutineContext().isActive) {
                market = updateMarket(market)
                val portfolioUiModel = getMyPortfolio().toPortfolioUiModel(market)
                val positions = getMyHoldings().map {
                    buildPosition(
                        it,
                        market[it.coinId]!!
                    ).toCoinCardUiModel()
                }
                emit(
                    DashboardUiState(
                        isLoading = false,
                        isMarketOpen = true,
                        portfolioUiModel = portfolioUiModel,
                        holdings = positions,
                        transactions = transactions.map { it.toTransactionListItemUiModel() },
                    )
                )
                delay(1000)
            }
        }
    }
}

