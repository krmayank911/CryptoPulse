package `in`.crypto.core_designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.crypto.core_designsystem.R
import `in`.crypto.core_designsystem.theme.CryptoTypography

val DM_MONO = FontFamily(
    Font(R.font.mono_medium, FontWeight.SemiBold),
            Font(R.font.mono_medium, FontWeight.Medium),
)

val DM_SANS = FontFamily(
    Font(R.font.sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.sans_medium, FontWeight.Medium),
    Font(R.font.sans_regular, FontWeight.Normal),
)

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPDisplayText(
    text: String = "$84,220.41",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.displayLarge,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPTitleText(
    modifier: Modifier = Modifier,
    text: String = "Portfolio Value",
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        style = CryptoTypography.titleMedium,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPSymbolText(
    text: String = "Portfolio Value",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.titleMedium,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(4.dp)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPHeadlineText(
    text: String = "Bitcoin (BTC)",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.headlineMedium,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPBodyText(
    modifier: Modifier = Modifier,
    text: String = "Real-time price updates every 250ms. Lifecycle-aware, minimal recompositions.",
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        style = CryptoTypography.bodyMedium,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPLabelText(
    text: String = "Bought Bitcoin",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.labelMedium,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPCaptionText(
    modifier: Modifier = Modifier,
    text: String = "Today · 8:14 AM",
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        style = CryptoTypography.bodySmall,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPDataText(
    text: String = "▲ +$1,843.20 · +2.24%",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.displaySmall,
        color = color
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun CPSectionText(
    text: String = "Your Assets",
    color: Color = Color.White
) {
    Text(
        text = text,
        style = CryptoTypography.labelSmall,
        color = color
    )
}

