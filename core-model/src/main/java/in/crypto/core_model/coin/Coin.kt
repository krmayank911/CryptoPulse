package `in`.crypto.core_model.coin

import androidx.compose.runtime.Immutable
import `in`.crypto.core_model.Money
import kotlinx.collections.immutable.PersistentList

@Immutable
data class Coin(
    val id: CoinId,
    val icon: String,
    val symbol: String,
    val currentPrice: Money,
    val priceHistory: PersistentList<Money>
)