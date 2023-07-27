package com.traction.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.devhassan.designsystem.ui.theme.black
import com.devhassan.designsystem.ui.theme.grey
import com.devhassan.designsystem.ui.theme.purple500
import com.devhassan.designsystem.ui.theme.purple700

private val DarkColorPalette = darkColorScheme(
    primary = purple500,
    inversePrimary = purple700,
    secondary = grey,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = black,
    onBackground = black,
    onSurface = black,
)

private val LightColorPalette = lightColorScheme(
    primary = purple500,
    inversePrimary = purple700,
    secondary = grey,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = black,
    onBackground = black,
    onSurface = black,
)

@Composable
fun CarbonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = tractionTypography,
        shapes = Shapes,
        content = content
    )
}