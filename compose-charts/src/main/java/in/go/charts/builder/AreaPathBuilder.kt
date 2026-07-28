package `in`.go.charts.builder

import androidx.compose.ui.graphics.Path
import `in`.go.charts.model.ChartPoint
import `in`.go.charts.renderer.ControlPointCalculator

object AreaPathBuilder {

    fun build(
        points: List<ChartPoint>,
        chartHeight: Float
    ): Path {
        val path = Path().apply {
            moveTo(points.first().x, chartHeight)
            lineTo(points.first().x, points.first().y)

            CurveBuilder.appendCurveFromSecondPoint(this, points)

            lineTo(points.last().x, chartHeight)
            close()
        }
        return path
    }

}