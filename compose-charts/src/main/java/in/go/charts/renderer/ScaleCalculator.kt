package `in`.go.charts.renderer

import `in`.go.charts.model.ChartPoint

object ScaleCalculator {

    fun calculate(
        values: List<Number>,
        width: Float,
        height: Float
    ): List<ChartPoint> {

        if (values.isEmpty()) return emptyList()

        if (values.size == 1) {
            return listOf(
                ChartPoint(
                    x = width / 2f,
                    y = height / 2f
                )
            )
        }

        val floatValues = values.map(Number::toFloat)
        val min = floatValues.min()
        val max = floatValues.max()
        val range = max-min
        val size = floatValues.size
        if (range == 0f) {
            val y = height/2f
            return List(floatValues.size) { index ->
                ChartPoint(
                    x = index * (width / (floatValues.lastIndex)),
                    y = y
                )
            }
        }
        val xStep = width / (size-1)
        return floatValues.mapIndexed { i, v ->
            val normalizedY = (v-min) / range
            val screenY = height * (1f - normalizedY)
            ChartPoint(
                x = i * xStep,
                y = screenY
            )
        }
    }

}