package `in`.crypto.feature_dashboard.actions

sealed interface DashboardAction {

    data object Refresh : DashboardAction
    data class CoinClick(val coinId: String) : DashboardAction
    data class TransactionClick(val transactionId: String) : DashboardAction
    data class Search(val query: String) : DashboardAction

}