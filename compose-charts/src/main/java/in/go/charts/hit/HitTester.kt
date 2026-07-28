package `in`.go.charts.hit

import android.util.Log
import `in`.go.charts.model.ChartPoint
import kotlin.math.abs

object HitTester {

    fun findNearest(
        points: List<ChartPoint>,
        touchX: Float
    ): Int? {

        if (points.isEmpty()) {
            Log.d("Chart", "findNearest: chart points are empty")
            return null
        }

        var nearestPoint = -1
        var minDistance = Float.MAX_VALUE

        points.forEachIndexed { index, point ->
            val distance = abs(point.x - touchX)
            if (distance < minDistance) {
                minDistance = distance
                nearestPoint = index
            }
        }
        return nearestPoint.takeIf { it > 0 }
    }

}