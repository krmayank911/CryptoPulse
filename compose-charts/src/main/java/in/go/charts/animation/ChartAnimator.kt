package `in`.go.charts.animation

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberCharAnimationProgress(
    durationMillis: Int = 800
): Float {

    var target by remember {
        mutableFloatStateOf(0f)
    }

    val progress by animateFloatAsState(
        targetValue = target,
        animationSpec = tween(durationMillis),
        label = "ChartAnimation"
    )

    LaunchedEffect(Unit) {
        Log.d("Chart", "Animation started")
        target = 1f
    }
    LaunchedEffect(progress) {
        Log.d("Chart", "Progress = $progress")
    }

    return progress
}