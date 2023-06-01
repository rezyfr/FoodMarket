package com.rezyfr.foodmarket.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rezyfr.foodmarket.feature.dashboard.home.HomeScreen
import com.rezyfr.foodmarket.ui.DashboardScreen
import com.rezyfr.foodmarket.feature.dashboard.order.OrderScreen
import com.rezyfr.foodmarket.feature.dashboard.profile.ProfileScreen

internal sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Order: Screen("order")
    object Profile: Screen("profile")
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun FMNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Order.route) {
            OrderScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}