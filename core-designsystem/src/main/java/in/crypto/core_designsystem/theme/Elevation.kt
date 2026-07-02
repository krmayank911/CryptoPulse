package `in`.crypto.core_designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Elevation(
    val card: Dp = 2.dp,
    val sheet: Dp = 12.dp
)
