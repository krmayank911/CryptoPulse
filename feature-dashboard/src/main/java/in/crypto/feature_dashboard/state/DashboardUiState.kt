package `in`.crypto.feature_dashboard.state

import androidx.compose.runtime.Immutable
import `in`.crypto.shared_ui.models.CoinCardUiModel
import `in`.crypto.shared_ui.models.PortfolioUiModel
import `in`.crypto.shared_ui.models.TransactionListItemUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class DashboardUiState(
    val isLoading: Boolean = false,
    val isMarketOpen: Boolean = false,
    val portfolioUiModel: PortfolioUiModel? = null,
    val holdings: PersistentList<CoinCardUiModel> = persistentListOf(),
    val transactions: PersistentList<TransactionListItemUiModel> = persistentListOf(),
    val searchQuery: String = "",
    val error: String? = null
)
