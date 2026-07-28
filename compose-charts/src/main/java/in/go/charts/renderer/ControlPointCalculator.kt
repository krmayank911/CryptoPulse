package `in`.go.charts.renderer

import `in`.go.charts.model.ControlPoints
import `in`.go.charts.model.ChartPoint

object ControlPointCalculator {

    fun calculateControlPoint(
        start: ChartPoint,
        end: ChartPoint
    ): ControlPoints {

        val dx = end.x - start.x

        return ControlPoints(
            first = ChartPoint(
                x = start.x + dx/3f,
                y = start.y
            ),
            second = ChartPoint(
                x = end.x - dx/3f,
                y = end.y
            )
        )

    }

}