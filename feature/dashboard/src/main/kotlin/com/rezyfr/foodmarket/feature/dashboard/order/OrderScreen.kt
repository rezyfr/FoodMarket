package com.rezyfr.foodmarket.feature.dashboard.order

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier){
        Text(text = "Order")
    }
}