package com.rezyfr.foodmarket

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.rezyfr.foodmarket.feature.dashboard.navigation.DashboardNavigationScreen
import com.rezyfr.foodmarket.ui.currentScreenAsState

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun rememberFMAppState(): FMAppState {

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    return remember(navController) {
        FMAppState(navController)
    }
}

@Stable
class FMAppState(
    val navController: NavHostController,
) {
    private val currentDestination: String?
        @Composable get() = navController.currentDestination?.route

    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination) {
            DashboardNavigationScreen.Home.route -> true
            DashboardNavigationScreen.Order.route -> true
            DashboardNavigationScreen.Profile.route -> true
            else -> false
        }
}