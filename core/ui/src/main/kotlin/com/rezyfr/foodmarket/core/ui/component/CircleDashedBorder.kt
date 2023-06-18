package com.rezyfr.foodmarket.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CircleDashedBorder(
    modifier: Modifier = Modifier,
    color: Color,
    radius: Float,
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color, radius = radius, style = Stroke(
                width = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        )
    }
}