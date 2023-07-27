package com.traction.core.designsystem.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun TractionTheme(
    content: @Composable () -> Unit
) {
    val tractionLightColorScheme = lightColorScheme(
        primary = primaryBlue,
        secondary = grey,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = black,
        onBackground = black,
        onSurface = black,
    )

    CompositionLocalProvider(LocalRippleTheme provides TractionRippleTheme) {
        MaterialTheme(
            colorScheme = tractionLightColorScheme,
            typography = tractionTypography,
            content = content,
        )
    }

}