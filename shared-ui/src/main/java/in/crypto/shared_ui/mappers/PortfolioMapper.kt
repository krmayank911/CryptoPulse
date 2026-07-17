package `in`.crypto.shared_ui.mappers

import `in`.crypto.core_model.portfolio.PortfolioMetrics
import `in`.crypto.shared_ui.models.CryptoState
import `in`.crypto.shared_ui.models.PortfolioUiModel

fun PortfolioMetrics.toPortfolioUiModel(): PortfolioUiModel {

    val investedAmount = investedAmount
    val currentValue = currentValue

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

    val percentageChange = String.format("%.2f", pnlPercentage)
    val percentageChangeText = "$percentageChange%"

    return PortfolioUiModel(
        value = currentValue.getValue(),
        changeAmount = changeAmountText,
        changePercentage = percentageChangeText,
        cryptoState = cryptoState
    )
}