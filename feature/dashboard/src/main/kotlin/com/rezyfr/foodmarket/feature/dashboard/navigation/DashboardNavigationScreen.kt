package com.rezyfr.foodmarket.feature.dashboard.navigation

internal sealed class DashboardNavigationScreen(val route: String) {
    object Home: DashboardNavigationScreen("home")
    object Order : DashboardNavigationScreen("order")
    object Profile : DashboardNavigationScreen("profile")
}