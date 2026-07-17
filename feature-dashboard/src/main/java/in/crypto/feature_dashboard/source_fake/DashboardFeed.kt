package `in`.crypto.feature_dashboard.source_fake

import `in`.crypto.core_model.market.PortfolioData
import kotlinx.coroutines.flow.Flow

interface DashboardFeed {

    fun observe(): Flow<PortfolioData>

}