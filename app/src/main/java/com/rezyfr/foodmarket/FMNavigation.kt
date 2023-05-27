package com.rezyfr.foodmarket

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

internal sealed class Screen(val route: String) {
    object SignIn : Screen("signin")
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
            SignInScreen()
        }
    }
}