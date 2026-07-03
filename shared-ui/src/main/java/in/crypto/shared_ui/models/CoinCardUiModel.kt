package `in`.crypto.shared_ui.models

import androidx.compose.runtime.Immutable
import `in`.crypto.core_model.Money
import kotlinx.collections.immutable.PersistentList


@Immutable
data class CoinCardUiModel(
    val id: String = "0",
    val symbol: String? = null,
    val icon: String? = null,
    val currentPrice: String? = null,
    val priceHistory: PersistentList<Money>? = null,
    val state: CryptoState = CryptoState.NEUTRAL,
    val changeText: String? = null
)

