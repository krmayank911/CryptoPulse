package `in`.crypto.core_model.portfolio

import `in`.crypto.core_model.Money

data class PortfolioMetrics(
    val investedAmount: Money,
    val currentValue: Money,
    val pnl: Money,
    val pnlPercentage: Float
)