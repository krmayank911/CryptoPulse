package `in`.go.charts.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChartSelection(
    val index: Int,
    val value: Number,
    val point: ChartPoint
)
