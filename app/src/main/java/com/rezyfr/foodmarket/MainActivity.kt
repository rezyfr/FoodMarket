package com.rezyfr.foodmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.rezyfr.foodmarket.theme.FoodMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FMContent()
        }
    }
    @Composable
    private fun FMContent() {
        FoodMarketTheme {
        }
    }
}