package `in`.crypto.core_designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import `in`.crypto.core_designsystem.components.DM_MONO
import `in`.crypto.core_designsystem.components.DM_SANS

val CryptoTypography = Typography(

    // display
    displayLarge = TextStyle(
        fontSize = 32.sp,
        fontFamily = DM_MONO,
        fontWeight = FontWeight.SemiBold,
        fontFeatureSettings = "tnum"
    ),

    // title
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.SemiBold
    ),

    // headline
    headlineMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.Medium
    ),

    // body
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.Medium
    ),

    // label
    labelMedium = TextStyle(
        fontSize = 13.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.Medium
    ),

    // caption
    bodySmall = TextStyle(
        fontSize = 11.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.Normal
    ),

    // data
    displaySmall = TextStyle(
        fontSize = 13.sp,
        fontFamily = DM_MONO,
        fontWeight = FontWeight.Medium,
        fontFeatureSettings = "tnum"
    ),

    // section
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontFamily = DM_SANS,
        fontWeight = FontWeight.Medium,
    )

)