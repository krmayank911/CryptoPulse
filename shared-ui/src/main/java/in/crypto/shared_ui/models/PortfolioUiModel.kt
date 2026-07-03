package `in`.crypto.shared_ui.models

import androidx.compose.runtime.Immutable

@Immutable
data class PortfolioUiModel(
    val value: String,
    val changeAmount: String,
    val changePercentage: String,
    val cryptoState: CryptoState
)
