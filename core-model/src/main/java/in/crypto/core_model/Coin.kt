package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class Coin(
    val id: String,
    val icon: String,
    val symbol: String,
    val currentPrice: Money,
    val priceHistory: List<Money>
)
