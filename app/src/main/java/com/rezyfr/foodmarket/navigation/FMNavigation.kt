package com.rezyfr.foodmarket.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.gson.Gson
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.dashboard.home.HomeScreen
import com.rezyfr.foodmarket.feature.dashboard.order.OrderScreen
import com.rezyfr.foodmarket.feature.dashboard.profile.ProfileScreen
import com.rezyfr.foodmarket.feature.order.food.FoodDetailScreen
import com.rezyfr.foodmarket.feature.order.payment.PaymentScreen
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Order : Screen("order")
    object Profile : Screen("profile")
    object FoodDetail : Screen("food_detail")
    object Payment : Screen("payment") {
        fun createRoute(paymentParams: PaymentParams, name: String): String {
            val gson = Gson()
            val paramsJson = gson.toJson(paymentParams)
            return "payment/$paramsJson/$name"
        }
    }
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
        modifier = modifier,
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
                navArgument("foodId") { type = NavType.IntType },
            )
        ) {
            FoodDetailScreen(
                openPayment = { payment, name, image ->
                    navController.navigate("${Screen.Payment.createRoute(payment, name)}")
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            "${Screen.Payment.route}/{payment_params}/{food_name}",
            arguments = listOf(
                navArgument("food_name") { type = NavType.StringType },
            )
        ) {
            val paramsJson = it.arguments?.getString("payment_params")
            val gson = Gson()
            val params = gson.fromJson(paramsJson, PaymentParams::class.java)
            PaymentScreen(
                navigateUp = {
                    navController.navigateUp()
                },
                openOngoingOrder = {
                    navController.navigate(Screen.Order.route)  {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                paymentParams = params
            )
        }
    }
}