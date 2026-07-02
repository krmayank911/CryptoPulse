package `in`.crypto.feature_dashboard.`source-fake`

import `in`.crypto.feature_dashboard.state.DashboardUiState
import kotlinx.coroutines.flow.Flow

interface DashboardFeed {

    fun observe(): Flow<DashboardUiState>

}