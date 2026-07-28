package `in`.go.charts.animation

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath

object PathTrimmer {

    /* Compose wrapper of Path Measure has bugs.
    fun trim(path: Path, progress: Float): Path {
        val output = Path()
        PathMeasure().apply {
            setPath(path, false)
            getSegment(
                0f,
                length * progress,
                output,
                true
            )
        }
        return output
    }
    */

    fun trim(path: Path, progress: Float): Path {
        val output = android.graphics.Path()
        val measure = android.graphics.PathMeasure(path.asAndroidPath(), false)
        val stop = measure.length * progress
        measure.getSegment(
            0f,
            stop,
            output,
            true
        )
        return output.asComposePath()
    }

}