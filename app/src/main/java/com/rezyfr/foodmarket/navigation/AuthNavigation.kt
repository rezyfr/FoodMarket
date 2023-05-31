package com.rezyfr.foodmarket.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rezyfr.foodmarket.feature.auth.signin.SignInScreen
import com.rezyfr.foodmarket.feature.auth.signup.SignUpScreen

internal sealed class AuthScreen(val route: String) {
    object SignIn : AuthScreen("signin")
    object SignUp : AuthScreen("signup")
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AuthNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreen.SignIn.route,
        modifier = modifier
    ) {
        composable(AuthScreen.SignIn.route) {
            SignInScreen(
                openSignUp = { navController.navigate(AuthScreen.SignUp.route) },
                openHome = {}
            )
        }
        composable(AuthScreen.SignUp.route) {
            SignUpScreen(
                onBackClicked = { navController.navigateUp() },
            )
        }
    }
}