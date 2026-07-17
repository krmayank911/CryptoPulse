package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import `in`.crypto.core_model.coin.Coin

@Immutable
data class Position(
    val coin: Coin,
    val holding: Holding,
    val investedAmount: Money,
    val currentValue: Money,
    val pnl: Money,
    val pnlPercentage: Float,
)
