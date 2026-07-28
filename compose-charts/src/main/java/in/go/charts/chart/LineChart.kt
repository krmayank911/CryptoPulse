package `in`.go.charts.chart

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.go.charts.animation.PathTrimmer
import `in`.go.charts.animation.rememberCharAnimationProgress
import `in`.go.charts.model.ChartStyle
import `in`.go.charts.builder.LinePathBuilder
import `in`.go.charts.hit.HitTester
import `in`.go.charts.model.ChartAnimationSpec
import `in`.go.charts.model.PointStyle
import `in`.go.charts.renderer.LineRenderer
import `in`.go.charts.renderer.PointRenderer
import `in`.go.charts.renderer.ScaleCalculator

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    values: List<Number>,
    chartStyle: ChartStyle,
    animationSpec: ChartAnimationSpec? = null,
    isInteractive: Boolean = false,
) {

    val progress = animationSpec?.let {
        rememberCharAnimationProgress(it.durationInMillis)
    } ?: 1f

    BoxWithConstraints(modifier) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val chartPoints = ScaleCalculator.calculate(values, width, height)

        val canvasModifier = Modifier.fillMaxSize()

        var selectedIndex by remember {
            mutableIntStateOf(-1)
        }

        if (isInteractive) {
            canvasModifier.pointerInput(chartPoints) {
                detectDragGestures(
                    onDragStart = { offset ->
                        selectedIndex = HitTester.findNearest(
                            chartPoints, offset.x
                        ) ?: -1
                    },
                    onDrag = { change, dragAmount ->
                        selectedIndex = HitTester.findNearest(
                            chartPoints, change.position.x
                        ) ?: -1
                    },
                    onDragEnd = {
                        selectedIndex = -1
                    }
                )
            }
        }

        Canvas(modifier = canvasModifier) {
            if (values.size < 2) return@Canvas
            val path = LinePathBuilder.build(chartPoints)
            val trimmedPath = PathTrimmer.trim(path, progress)

            with(LineRenderer) {
                draw(
                    path = trimmedPath,
                    color = chartStyle.lineColor,
                    strokeWidth = chartStyle.strokeWidth.toPx()
                )
            }

            if (selectedIndex >= 0) {
                with(PointRenderer) {
                    draw(
                        point = chartPoints[selectedIndex],
                        style = PointStyle.defaultStyle().copy(
                            color = chartStyle.lineColor
                        )
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
@Composable
fun LineChartPreview(modifier: Modifier = Modifier) {

    LineChart(
        modifier = modifier.fillMaxWidth()
            .height(120.dp),
        values = listOf(10f, 20f, 15f, 30f, 28f, 35f, 18f, 28f),
        chartStyle = ChartStyle.defaultStyle(),
        isInteractive = true
    )

}