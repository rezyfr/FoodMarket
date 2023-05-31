package com.rezyfr.foodmarket.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rezyfr.foodmarket.feature.auth.signin.SignInScreen
import com.rezyfr.foodmarket.feature.auth.signup.SignUp
import com.rezyfr.foodmarket.feature.auth.signup.SignUpScreen
import com.rezyfr.foodmarket.feature.dashboard.DashboardScreen

internal sealed class Screen(val route: String) {
    object Dashboard: Screen("dashboard")
    object Order: Screen("order")
    object Profile: Screen("profile")
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun FMNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(Screen.Order.route) {
            DashboardScreen()
        }
        composable(Screen.Profile.route) {
            DashboardScreen()
        }
    }
}