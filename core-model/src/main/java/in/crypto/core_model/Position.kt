package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Position(
    val coin: Coin,
    val holding: Holding,
    val currentValue: Money,
    val pnl: Money,
    val pnlPercentage: Float,
)
