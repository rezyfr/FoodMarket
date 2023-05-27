package com.rezyfr.foodmarket.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VSpacer(height: Int = 8) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun HSpacer(width: Int = 8) {
    Spacer(modifier = Modifier.width(width.dp))
}