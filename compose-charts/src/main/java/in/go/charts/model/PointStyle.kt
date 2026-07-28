package `in`.go.charts.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class PointStyle(
    val color: Color,
    val radius: Dp
) {

    companion object {

        fun defaultStyle(): PointStyle {
            return PointStyle (
                color = Color.Red,
                radius = 4.dp
            )
        }

    }

}
