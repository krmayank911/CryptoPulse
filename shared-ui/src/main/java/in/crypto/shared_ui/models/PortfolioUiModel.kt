package `in`.crypto.shared_ui.models

data class PortfolioUiModel(
    val value: String,
    val changeAmount: String,
    val changePercentage: String,
    val cryptoState: CryptoState
)
