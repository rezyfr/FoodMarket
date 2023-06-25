package com.rezyfr.foodmarket.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun FoodMarketTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomTypography provides foodMarketTypography
    ) {
        MaterialTheme(
            colors = FMColor,
            content = content
        )
    }
}

object FMTheme {
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
}