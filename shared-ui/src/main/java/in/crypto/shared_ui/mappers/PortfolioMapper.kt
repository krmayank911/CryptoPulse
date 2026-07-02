package `in`.crypto.shared_ui.mappers

import `in`.crypto.core_model.Coin
import `in`.crypto.core_model.portfolio.Portfolio
import `in`.crypto.core_model.helper.PortfolioCalculator
import `in`.crypto.shared_ui.models.CryptoState
import `in`.crypto.shared_ui.models.PortfolioUiModel

fun Portfolio.toPortfolioUiModel(market: Map<String, Coin>): PortfolioUiModel {

    val portfolioMetrics = PortfolioCalculator.calculate(this, market)

    val investedAmount = portfolioMetrics.investedAmount
    val currentValue = portfolioMetrics.currentValue

    val cryptoState = when {
        currentValue.amount > investedAmount.amount -> CryptoState.PROFIT
        currentValue.amount < investedAmount.amount -> CryptoState.LOSS
        else -> CryptoState.NEUTRAL
    }

    val symbol = when(cryptoState) {
        CryptoState.PROFIT -> "▲ +"
        CryptoState.LOSS -> "▼ -"
        else -> ""
    }

    val changeAmount = currentValue - investedAmount

    val changeAmountText = when (cryptoState) {
        CryptoState.PROFIT -> "$symbol${changeAmount.getValue()}"
        CryptoState.LOSS -> "$symbol${changeAmount.getValue()}"
        else -> changeAmount.getValue()
    }

    val percentageChange = String.format("%.2f", portfolioMetrics.pnlPercentage)
    val percentageChangeText = "$percentageChange%"

    return PortfolioUiModel(
        value = currentValue.getValue(),
        changeAmount = changeAmountText,
        changePercentage = percentageChangeText,
        cryptoState = cryptoState
    )
}