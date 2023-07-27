package com.traction.core.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal object TractionRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = primaryBlue

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        MaterialTheme.colorScheme.primaryContainer,
        lightTheme = true
    )
}