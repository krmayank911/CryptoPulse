package `in`.crypto.shared_ui.mappers

import `in`.crypto.core_model.Position
import `in`.crypto.shared_ui.models.CryptoState
import `in`.crypto.shared_ui.models.CoinCardUiModel
import java.math.BigDecimal

fun Position.toCoinCardUiModel(showPercentageChange: Boolean = false): CoinCardUiModel {

    val state = when {
        pnl.amount > BigDecimal.ZERO -> CryptoState.PROFIT
        pnl.amount < BigDecimal.ZERO -> CryptoState.LOSS
        else -> CryptoState.NEUTRAL
    }
    val symbol = when(state) {
        CryptoState.PROFIT -> "▲ +"
        CryptoState.LOSS -> "▼ -"
        else -> ""
    }

    val changeText = if (showPercentageChange) {
        val change = String.format("%.2f", pnlPercentage)
        "$symbol$change%"
    } else {
        "$symbol${pnl.getValue()}"
    }

    return CoinCardUiModel(
        id = coin.id,
        icon = coin.icon,
        symbol = coin.symbol,
        currentPrice = currentValue.getValue(),
        priceHistory = coin.priceHistory,
        state = state,
        changeText = changeText
    )
}