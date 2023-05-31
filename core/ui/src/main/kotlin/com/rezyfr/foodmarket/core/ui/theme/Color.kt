package com.rezyfr.foodmarket.core.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val yellow500 = Color(0xFFFFC700)
private val grey500 = Color(0xFF8D92A3)
private val black100 = Color(0xFF020202)
private val red500 = Color(0xFFD9435E)
private val red700 = Color(0xFFFF5C00)
private val teal500 = Color(0xFF1ABC9C)
val FMColor = lightColors(
    primary = yellow500,
    primaryVariant = red700,
    onPrimary = black100,
    onError = Color.White,
    secondary = grey500,
    onSecondary = Color.White,
    surface = Color.White,
    onSurface = black100,
    onBackground = black100,
    secondaryVariant = teal500,
    background = Color.White,
    error = red500,
)