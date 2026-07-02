package `in`.crypto.core_designsystem.tokens

import androidx.compose.ui.graphics.Color

object Colors {

    val White = Color(0xFFFFFFFF)

    val Profit = Color(0xFF39D98A)

    val Loss = Color(0xFFFF6B6B)

    val Interactive = Color(0xFF00C9FF)

    val Warning = Color(0xFFF5A623)

    val Page = Color(0xFF0A0D14)

    val Sidebar = Color(0xFF111620)
    val Card = Color(0xFF161C28)

    val Elevated = Color(0xFF1C2436)

    val Shimmer = Color(0xFF222C40)

    val TextPrimary = Color(0xFFE8EDF5)
    val TextSecondary = Color(0xFF8A96AA)
    val TextMuted = Color(0xFF5A6478)
    val TextFaint = Color(0xFF3A4255)

    val TextDarkOnAccent = Color(0xFF001822)

    val defaultBorder = Color(0x0FFFFFFF)
    val interactiveBorder = Color(0x1AFFFFFF)
    val emphasisBorder = Color(0x2EFFFFFF)
    val lossBorder = Color(0x40FF6B6B)

    val lossSub = Color(0x1AFF6B6B)
    val gainSub = Color(0x1A39D98A)
    val accentBorder =  Color(0x4C00C9FF)
    val gainBorder = Color(0x4039D98A)
    val ChartLine = Color(0xFF60A5FA)
    val ChartFill = Color(0x402563EB)

}

interface CPTheme {
    val appBackground: Color
    val sidebarBackground: Color
    val cardBackground: Color
    val elevatedBackground: Color
    val shimmerBackground: Color

    val gainColor: Color
    val lossColor: Color
    val accentColor: Color
    val warningColor: Color

    val textColorPrimary: Color
    val textColorSecondary: Color
    val textColorMuted: Color
    val textColorFaint: Color
    val textColorAccent: Color
    val textColorContrast: Color

    val borderColorDefault: Color
    val borderColorInteractive: Color
    val borderColorEmphasis: Color
    val borderColorGain: Color
    val borderColorLoss: Color
    val borderColorAccent: Color

    val gainBackground: Color
    val lossBackground: Color
}

object CPThemeDark : CPTheme {
    override val appBackground = Colors.Page
    override val sidebarBackground = Colors.Sidebar
    override val cardBackground = Colors.Card
    override val elevatedBackground = Colors.Elevated
    override val shimmerBackground = Colors.Shimmer

    override val gainColor = Colors.Profit
    override val lossColor = Colors.Loss
    override val accentColor = Colors.Interactive
    override val warningColor = Colors.Warning

    override val textColorPrimary = Colors.TextPrimary
    override val textColorSecondary = Colors.TextSecondary
    override val textColorMuted = Colors.TextMuted
    override val textColorFaint = Colors.TextFaint
    override val textColorAccent = Colors.TextDarkOnAccent
    override val textColorContrast = Colors.White

    override val borderColorDefault = Colors.defaultBorder
    override val borderColorInteractive = Colors.interactiveBorder
    override val borderColorEmphasis = Colors.emphasisBorder
    override val borderColorGain = Colors.gainBorder
    override val borderColorLoss = Colors.lossBorder
    override val borderColorAccent = Colors.accentBorder

    override val gainBackground = Colors.gainSub
    override val lossBackground = Colors.lossSub
}