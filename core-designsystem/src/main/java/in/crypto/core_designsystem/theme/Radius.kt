package `in`.crypto.core_designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Radius(
    val small: Dp = 8.dp,
    val medium: Dp = 14.dp,
    val large: Dp = 24.dp
)
