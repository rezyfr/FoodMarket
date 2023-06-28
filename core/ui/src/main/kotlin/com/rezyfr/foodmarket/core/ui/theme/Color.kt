package com.rezyfr.foodmarket.core.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val yellow = Color(0xFFFEE54C)
private val black = Color(0xFF17181E)
private val grey = Color(0xFF9E9D9D)
private val white = Color(0xFFFFFFFF)

@SuppressLint("ConflictingOnColor")
val FMColor = lightColors(
    primary = yellow,
    onPrimary = black,
    onError = white,
    secondary = grey,
    onBackground = black,
    surface = grey.copy(alpha = 0.1f),
    onSurface = grey,
    background = white,
)