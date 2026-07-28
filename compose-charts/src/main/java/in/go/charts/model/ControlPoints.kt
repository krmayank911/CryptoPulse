package `in`.go.charts.model

import androidx.compose.runtime.Immutable

@Immutable
data class ControlPoints(
    val first: ChartPoint,
    val second: ChartPoint
)
