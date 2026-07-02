package `in`.crypto.core_designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import `in`.crypto.core_designsystem.tokens.SPACE_1
import `in`.crypto.core_designsystem.tokens.SPACE_2
import `in`.crypto.core_designsystem.tokens.SPACE_3
import `in`.crypto.core_designsystem.tokens.SPACE_4
import `in`.crypto.core_designsystem.tokens.SPACE_5

@Immutable
data class Spacing(
    val xs: Dp = SPACE_1,
    val sm: Dp = SPACE_2,
    val md: Dp = SPACE_3,
    val lg: Dp = SPACE_4,
    val xl: Dp = SPACE_5
)