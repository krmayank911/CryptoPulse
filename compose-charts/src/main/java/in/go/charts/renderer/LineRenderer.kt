package `in`.go.charts.renderer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

object LineRenderer {

    fun DrawScope.draw(
        path: Path,
        color: Color,
        strokeWidth: Float
    ) {
       drawPath(
           path,
           color,
           style = Stroke(
               width = strokeWidth,
               cap = StrokeCap.Round,
               join = StrokeJoin.Round
           )
       )
    }

}