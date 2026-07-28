package `in`.go.charts.renderer

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

object AreaRenderer {

    fun DrawScope.draw (
        areaPath: Path,
        brush: Brush
    ) {
        drawPath(
            path = areaPath,
            brush = brush,
            style = Fill
        )
    }

}