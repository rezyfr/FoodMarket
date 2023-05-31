package com.rezyfr.foodmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.navigation.FMNavigation
import com.rezyfr.foodmarket.ui.FMMainApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun FMContent() {
        FoodMarketTheme {
            FMMainApp()
        }
    }
}