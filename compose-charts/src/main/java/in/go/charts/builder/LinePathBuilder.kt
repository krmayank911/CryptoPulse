package `in`.go.charts.builder

import androidx.compose.ui.graphics.Path
import `in`.go.charts.model.ChartPoint

object LinePathBuilder {

    fun build(points: List<ChartPoint>): Path {
        val path = Path().apply {
            CurveBuilder.appendCurve(this, points)
        }
        return path
    }

}