package `in`.go.charts.model

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.Immutable

@Immutable
data class ChartAnimationSpec(
    val enabled: Boolean = true,
    val durationInMillis: Int = 800,
    val easing: Easing = FastOutSlowInEasing
)
