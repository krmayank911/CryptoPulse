package `in`.crypto.shared_ui.models

import `in`.crypto.core_model.Money


data class CoinCardUiModel(
    val id: String = "0",
    val symbol: String? = null,
    val icon: String? = null,
    val currentPrice: String? = null,
    val priceHistory: List<Money>? = null,
    val state: CryptoState = CryptoState.NEUTRAL,
    val changeText: String? = null
)

