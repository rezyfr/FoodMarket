package com.rezyfr.foodmarket

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.rezyfr.foodmarket.feature.auth.signin.SignInScreen
import com.rezyfr.foodmarket.feature.auth.signin.SignUpButton
import com.rezyfr.foodmarket.feature.auth.signup.SignUp
import com.rezyfr.foodmarket.feature.auth.signup.SignUpScreen
import com.rezyfr.foodmarket.feature.dashboard.DashboardScreen

internal sealed class Screen(val route: String) {
    object SignIn : Screen("signin")
    object SignUp: Screen("signup")
    object Dashboard: Screen("dashboard")
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun FMNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
        modifier = modifier
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(
                openSignUp = { navController.navigate(Screen.SignUp.route) },
                openHome = { navController.navigate(Screen.Dashboard.route) }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onBackClicked = { navController.navigateUp() },
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
    }
}