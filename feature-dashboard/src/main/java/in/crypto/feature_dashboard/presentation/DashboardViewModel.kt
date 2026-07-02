package `in`.crypto.feature_dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.crypto.core_model.helper.PositionGenerator.buildPosition
import `in`.crypto.fake.coins.getCoinById
import `in`.crypto.fake.coins.getMarket
import `in`.crypto.fake.portfolio.getMyPortfolio
import `in`.crypto.feature_dashboard.`source-fake`.DashboardFeed
import `in`.crypto.feature_dashboard.state.DashboardUiState
import `in`.crypto.shared_ui.mappers.toCoinCardUiModel
import `in`.crypto.shared_ui.mappers.toPortfolioUiModel
import `in`.crypto.shared_ui.mappers.toTransactionListItemUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    val dashboardFeed: DashboardFeed
): ViewModel() {

    private val uiState: MutableStateFlow<DashboardUiState> = MutableStateFlow(DashboardUiState(isLoading = true))
    val uiStateFlow: StateFlow<DashboardUiState> = uiState

    init {
        viewModelScope.launch {
            dashboardFeed.observe().collect {
                uiState.value = it
            }
        }
    }

    fun getDashboardData() {
        val dashboardState =  DashboardUiState(
            portfolioUiModel = getMyPortfolio().toPortfolioUiModel(getMarket()),
            holdings = getMyPortfolio().holdings.map { buildPosition(it, getCoinById(it.coinId)).toCoinCardUiModel() },
            transactions = getMyPortfolio().transactions.map { it.toTransactionListItemUiModel() }
        )
        uiState.value = dashboardState
    }

}