package `in`.crypto.feature_dashboard.state

import `in`.crypto.shared_ui.models.CoinCardUiModel
import `in`.crypto.shared_ui.models.PortfolioUiModel
import `in`.crypto.shared_ui.models.TransactionListItemUiModel

data class DashboardUiState(
    val isLoading: Boolean = false,
    val isMarketOpen: Boolean = false,
    val portfolioUiModel: PortfolioUiModel? = null,
    val holdings: List<CoinCardUiModel> = emptyList(),
    val transactions: List<TransactionListItemUiModel> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)
