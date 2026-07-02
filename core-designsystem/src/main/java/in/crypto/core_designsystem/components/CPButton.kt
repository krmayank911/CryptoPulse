package `in`.crypto.core_designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.crypto.core_designsystem.theme.CryptoTypography
import `in`.crypto.core_designsystem.theme.Radius
import `in`.crypto.core_designsystem.tokens.CPTheme
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.core_designsystem.tokens.SPACE_2


@Composable
fun CPButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    borderStroke: BorderStroke? = null,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    text: String = "Click Me",
    textColor: Color = Color.White,
    textStyle: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit = {}
) {

    val shape = RoundedCornerShape(Radius().small)

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        border = borderStroke,
        colors = buttonColors
    ) {

        if (icon != null) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = icon,
                contentDescription = null,
                tint = textColor
            )
            Spacer(Modifier.size(SPACE_2))
        }
        Text(
            text = text,
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
            ,
            color = textColor,
            style = textStyle
        )
    }
}

@Composable
@Preview
fun CPPrimaryButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String = "Click Me",
    onClick: () -> Unit = {},
    theme: CPTheme = CPThemeDark
) {

    val colors = ButtonDefaults.buttonColors().copy(containerColor = theme.accentColor,)
    val textColor = theme.textColorAccent
    val textStyle = CryptoTypography.bodyMedium

    CPButton(
        modifier = modifier,
        icon = icon,
        buttonColors = colors,
        text = text,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}

@Composable
@Preview
fun CPPrimaryButtonPositive(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String = "Click Me",
    onClick: () -> Unit = {},
    theme: CPTheme = CPThemeDark
) {

    val colors = ButtonDefaults.buttonColors().copy(containerColor = theme.gainColor)
    val textColor = theme.textColorAccent
    val textStyle = CryptoTypography.bodyMedium

    CPButton(
        modifier = modifier,
        icon = icon,
        buttonColors = colors,
        text = text,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}

@Composable
@Preview
fun CPSecondaryButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String = "Click Me",
    onClick: () -> Unit = {},
    theme: CPTheme = CPThemeDark
) {
    val borderStroke = BorderStroke(0.5.dp, theme.borderColorDefault)
    val colors = ButtonDefaults.buttonColors().copy(containerColor = theme.elevatedBackground)
    val textColor = theme.textColorPrimary
    val textStyle = CryptoTypography.bodyMedium
    CPButton(
        modifier = modifier,
        buttonColors = colors,
        borderStroke = borderStroke,
        icon = icon,
        text = text,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}

@Composable
@Preview
fun CPNegativeButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String = "Click Me",
    onClick: () -> Unit = {},
    theme: CPTheme = CPThemeDark
) {
    val borderStroke = BorderStroke(0.5.dp, theme.borderColorLoss)
    val colors = ButtonDefaults.buttonColors().copy(containerColor = theme.lossBackground)
    val textColor = theme.lossColor
    val textStyle = CryptoTypography.bodyMedium

    CPButton(
        modifier = modifier,
        buttonColors = colors,
        borderStroke = borderStroke,
        icon = icon,
        text = text,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}