package com.rezyfr.foodmarket.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.rezyfr.foodmarket.feature.dashboard.home.HomeScreen
import com.rezyfr.foodmarket.feature.dashboard.order.OrderScreen
import com.rezyfr.foodmarket.feature.dashboard.profile.ProfileScreen
import com.rezyfr.foodmarket.feature.order.food.FoodDetailScreen

internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Order : Screen("order")
    object Profile : Screen("profile")
    object FoodDetail : Screen("food_detail")
    object Payment : Screen("payment")
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
            HomeScreen(
                openFoodDetail = { foodId ->
                    navController.navigate("${Screen.FoodDetail.route}/$foodId")
                }
            )
        }
        composable(Screen.Order.route) {
            OrderScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(
            "${Screen.FoodDetail.route}/{foodId}",
            arguments = listOf(
                navArgument("foodId") { type = NavType.StringType },
            )
        ) {
            FoodDetailScreen(
                openPayment = {
                    navController.navigate(Screen.Payment.route) {

                    }
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}