package `in`.go.charts.tooltip

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.go.charts.model.ChartPoint
import `in`.go.charts.model.ChartSelection

@Preview
@Composable
fun DefaultTooltip(
    selection: ChartSelection = ChartSelection(0, 40.86, point = ChartPoint(30f, 24f)),
) {
    Text(
        modifier = Modifier
            .padding(4.dp),
        text = "$${selection.value}",
        color = Color.Red
    )
}