package com.rezyfr.foodmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rezyfr.foodmarket.theme.FoodMarketTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition{viewModel.shouldShowSplash.value}

        setContent {
            FMContent()
        }
    }
    @Composable
    private fun FMContent() {
        FoodMarketTheme {
            Box {
                Text(text = "Hello World!")
            }
        }
    }
}