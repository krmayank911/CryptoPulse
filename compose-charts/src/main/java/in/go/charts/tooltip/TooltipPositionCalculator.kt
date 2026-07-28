package `in`.go.charts.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import `in`.go.charts.model.ChartPoint

object TooltipPositionCalculator {

    fun calculate(
        point: ChartPoint,
        tooltipSize: IntSize,
        chartSize: Size,
        margin: Float
    ): Offset {

        var x = 0f
        var y = 0f

        // showOnLeft
        x = if (point.x + tooltipSize.width + (2 * margin) >= chartSize.width) {
            point.x - margin - tooltipSize.width
        } else {
            point.x + margin
        }

        y = if (point.y - tooltipSize.height - (2 * margin) <= 0) {
            point.y + margin
        } else if (point.y + tooltipSize.height/2 >= chartSize.height) {
            point.y - tooltipSize.height
        } else {
            point.y - (tooltipSize.height/2)
        }

        if (y > point.y) {
            x = point.x - (tooltipSize.width/2)
        }

        return Offset(x, y)
    }

}