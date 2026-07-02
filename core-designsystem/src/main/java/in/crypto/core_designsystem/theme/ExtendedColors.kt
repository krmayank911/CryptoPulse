package `in`.crypto.core_designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val profit: Color,
    val loss: Color,
    val chartLine: Color,
    val gainBackground: Color,
    val lossBackground: Color
)

val LocalExtendedColors = staticCompositionLocalOf<ExtendedColors> {
    error("No colors provided")
}
