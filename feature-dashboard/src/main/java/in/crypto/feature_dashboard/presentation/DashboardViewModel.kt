package `in`.crypto.feature_dashboard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.crypto.core_model.Position
import `in`.crypto.core_model.coin.CoinId
import `in`.crypto.core_model.market.PortfolioData
import `in`.crypto.feature_dashboard.mapper.calculatePositions
import `in`.crypto.feature_dashboard.mapper.toCoinUiModels
import `in`.crypto.feature_dashboard.mapper.toDashboardUiState
import `in`.crypto.feature_dashboard.source_fake.DashboardFeed
import `in`.crypto.feature_dashboard.state.DashboardUiState
import `in`.crypto.shared_ui.models.CoinCardUiModel
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    val dashboardFeed: DashboardFeed
): ViewModel() {

    private val _uiState: MutableStateFlow<DashboardUiState> = MutableStateFlow(DashboardUiState(isLoading = true))
    val uiStateFlow: StateFlow<DashboardUiState> = _uiState

    private var previousPortfolioData: PortfolioData? = null
    private var previousPositions: PersistentMap<CoinId, Position> = persistentMapOf()
    private var previousCoinCards: PersistentMap<CoinId, CoinCardUiModel> = persistentMapOf()

    init {
        viewModelScope.launch {
            dashboardFeed.observe().collect { portfolioData ->

                val positions = portfolioData.calculatePositions(previousPositions)
                val coinUiModels = positions.toCoinUiModels(previousPositions, previousCoinCards)

                previousPositions = positions
                previousCoinCards = coinUiModels

                _uiState.update {
                    portfolioData.toDashboardUiState(previousPositions, coinUiModels)
                }
                Log.d("CPulse", "DashboardUiState emitted")
            }
        }
    }

}