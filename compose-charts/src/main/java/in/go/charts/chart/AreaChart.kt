package `in`.go.charts.chart

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import `in`.go.charts.builder.AreaPathBuilder
import `in`.go.charts.builder.LinePathBuilder
import `in`.go.charts.hit.HitTester
import `in`.go.charts.model.ChartSelection
import `in`.go.charts.model.ChartStyle
import `in`.go.charts.model.PointStyle
import `in`.go.charts.renderer.AreaRenderer
import `in`.go.charts.renderer.LineRenderer
import `in`.go.charts.renderer.PointRenderer
import `in`.go.charts.renderer.ScaleCalculator
import `in`.go.charts.tooltip.DefaultTooltip
import `in`.go.charts.tooltip.TooltipPositionCalculator
import kotlin.math.roundToInt

@Composable
fun AreaChart(
    modifier: Modifier = Modifier,
    values: List<Number>,
    chartStyle: ChartStyle = ChartStyle.defaultAreaStyle(),
    tooltip: @Composable (ChartSelection) -> Unit? = { selection -> DefaultTooltip(selection = selection) },
    isInteractive: Boolean = false
) {
    checkNotNull(chartStyle.fillBrush)

    BoxWithConstraints(modifier) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val chartPoints = ScaleCalculator.calculate(values, width, height)

        var selectedIndex by remember { mutableIntStateOf(-1) }

        val canvasModifier = if (isInteractive) {
            Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            selectedIndex = HitTester.findNearest(
                                chartPoints, offset.x
                            ) ?: -1
                        },
                        onDrag = { change, _ ->
                            selectedIndex = HitTester.findNearest(
                                chartPoints, change.position.x
                            ) ?: -1
                        },
                        onDragEnd = {
                            selectedIndex = -1
                        }
                    )
                }
        } else {
            Modifier.fillMaxSize()
        }

        Canvas(modifier = canvasModifier) {
            if (values.size < 2) return@Canvas

            val path = AreaPathBuilder.build(chartPoints, size.height)

            with(AreaRenderer) {
                draw(
                    areaPath = path,
                    brush = chartStyle.fillBrush
                )
            }

            Log.d("Chart", "AreaChart: selectedIndex $selectedIndex")

            if (selectedIndex >= 0) {
                with(PointRenderer) {
                    draw(
                        chartPoints[selectedIndex],
                        PointStyle.defaultStyle().copy(
                            color = chartStyle.lineColor
                        )
                    )
                }
            }
        }

        val selection = selectedIndex.takeIf { it >= 0 }?.let {
            ChartSelection(
                index = selectedIndex,
                value = values[selectedIndex],
                point = chartPoints[selectedIndex]
            )
        }

        var tooltipSize by remember {
            mutableStateOf(IntSize.Zero)
        }

        selection?.let {

            val offset = TooltipPositionCalculator.calculate(
                point = it.point,
                tooltipSize = tooltipSize,
                chartSize = Size(width, height),
                margin = 8.dp.dpToPx()
            )

            Box(modifier = Modifier
                .offset {
                    IntOffset(
                        offset.x.roundToInt(),
                        offset.y.roundToInt()
                    )
                }
                .onSizeChanged { size ->
                    tooltipSize = size
                }
                .border(
                    width = 1.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(size = 2.dp)
                )
            ) {
                tooltip(it)
            }
        }
    }


}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun DominateCurveAreaChart(
    modifier: Modifier = Modifier,
    values: List<Number>,
    chartStyle: ChartStyle = ChartStyle.defaultAreaStyle()
) {
    checkNotNull(chartStyle.fillBrush)
    Canvas(modifier) {
        if (values.size < 2) return@Canvas

        val chartPoints = ScaleCalculator.calculate(values, size.width, size.height)

        val areaPath = AreaPathBuilder.build(chartPoints, size.height)
        val linePath = LinePathBuilder.build(chartPoints)

        with(AreaRenderer) {
            draw(
                areaPath = areaPath,
                brush = chartStyle.fillBrush
            )
        }

        with(LineRenderer) {
            draw(
                path = linePath,
                color = chartStyle.lineColor,
                strokeWidth = chartStyle.strokeWidth.toPx()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
@Composable
fun AreaChartPreview(modifier: Modifier = Modifier) {
    AreaChart(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        values = listOf(10f, 20f, 15f, 30f, 28f, 35f, 18f, 25f),
        chartStyle = ChartStyle.defaultAreaStyle(),
        isInteractive = true
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
@Composable
fun DCAreaChartPreview(modifier: Modifier = Modifier) {
    DominateCurveAreaChart(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        values = listOf(10f, 20f, 15f, 30f, 28f, 35f, 18f, 25f),
        chartStyle = ChartStyle.defaultAreaStyle().copy(
            strokeWidth = 1.dp
        )
    )
}