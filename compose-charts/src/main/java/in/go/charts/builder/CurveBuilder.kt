package `in`.go.charts.builder

import androidx.compose.ui.graphics.Path
import `in`.go.charts.model.ChartPoint
import `in`.go.charts.renderer.ControlPointCalculator

internal object CurveBuilder {

    fun appendCurve(
        path: Path,
        points: List<ChartPoint>,
        fromSecondPoint: Boolean = false
    ) {

        if (!fromSecondPoint) {
            path.moveTo(points.first().x, points.first().y)
        }

        for (i in 1 until points.size) {
            val controlPoints = ControlPointCalculator
                .calculateControlPoint(
                    start = points[i-1],
                    end = points[i]
                )
            path.cubicTo(
                x1 = controlPoints.first.x,
                y1 = controlPoints.first.y,
                x2 = controlPoints.second.x,
                y2 = controlPoints.second.y,
                x3 = points[i].x,
                y3 = points[i].y
            )
        }
    }

    fun appendCurveFromSecondPoint(
        path: Path,
        points: List<ChartPoint>
    ) {
        appendCurve(path, points, true)
    }

}