package com.traction.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.traction.core.designsystem.R


private val roboto: FontFamily = FontFamily(

    Font(R.font.athletics_light, weight = FontWeight.Light),
    Font(R.font.athletics_light_italic, weight = FontWeight.Light),
    Font(R.font.athletics_regular, weight = FontWeight.Normal),
    Font(R.font.athletics_regular_italic, weight = FontWeight.Normal),
    Font(R.font.athletics_medium, weight = FontWeight.Medium),
    Font(R.font.athletics_medium_italic, weight = FontWeight.Medium),
    Font(R.font.athletics_bold, weight = FontWeight.Bold),
    Font(R.font.athletics_bold_italic, weight = FontWeight.Bold),
    Font(R.font.athletics_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.athletics_extrabold_italic, weight = FontWeight.ExtraBold),
    Font(R.font.athletics_black, weight = FontWeight.Black),
    Font(R.font.athletics_black_italic, weight = FontWeight.Black),
)

internal val tractionTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
    ),
)
