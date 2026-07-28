package `in`.go.charts.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ChartStyle(
    val lineColor: Color,
    val strokeWidth: Dp,
    val fillBrush: Brush? = null,
    val strokeCap: StrokeCap = StrokeCap.Round,
    val strokeJoin: StrokeJoin = StrokeJoin.Round
) {

    companion object {
        fun defaultStyle(): ChartStyle {
            return ChartStyle(
                lineColor = Color.Green,
                strokeWidth = 2.dp
            )
        }

        fun defaultAreaStyle(): ChartStyle {
            return defaultStyle().copy(
                fillBrush = Brush.verticalGradient(
                    colors = listOf(Color.Green.copy(0.5f), Color.Green.copy(0.1f))
                )
            )
        }
    }
}
