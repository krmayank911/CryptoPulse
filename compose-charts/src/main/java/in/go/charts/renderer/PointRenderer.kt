package `in`.go.charts.renderer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import `in`.go.charts.model.ChartPoint
import `in`.go.charts.model.PointStyle

object PointRenderer {

    fun DrawScope.draw(
        point: ChartPoint,
        style: PointStyle
    ) {

        drawCircle(
            color = style.color,
            radius = style.radius.toPx(),
            center = Offset(point.x, point.y)
        )
    }

}