package com.rezyfr.foodmarket.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.rezyfr.foodmarket.feature.dashboard.R
import com.rezyfr.foodmarket.feature.dashboard.navigation.DashboardNavigationScreen
import com.rezyfr.foodmarket.navigation.FMNavigation

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    Scaffold(
        bottomBar = {
            val currentSelectedItem by navController.currentScreenAsState()
            DashboardBottomNavigation(
                selectedNavigation = currentSelectedItem,
                onNavigationSelected = { selected ->
                    navController.navigate(selected.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) {
        FMNavigation(Modifier.padding(it), navController)
    }
}

@Composable
internal fun DashboardBottomNavigation(
    selectedNavigation: DashboardNavigationScreen,
    onNavigationSelected: (DashboardNavigationScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color(0xFFE2E2E2),
        modifier = modifier,
    ) {
        DashboardNavItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    DashboardBottomNavICon(
                        item = item,
                        selected = selectedNavigation == item.screen,
                    )
                },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) },
            )
        }
    }
}
@Composable
private fun DashboardBottomNavICon(
    item: DashboardNavItem.Item,
    selected: Boolean,
    haveNotification: Boolean = false,
) {
    val icon = if (selected) item.selectedIconRes else item.iconRes

    Box {
        if (haveNotification) {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primaryVariant, shape = CircleShape)
                    .size(6.dp)
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            )
        }
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

private sealed class DashboardNavItem(
    val screen: DashboardNavigationScreen,
) {
    class Item(
        screen: DashboardNavigationScreen,
        @DrawableRes val iconRes: Int,
        @DrawableRes val selectedIconRes: Int
    ) : DashboardNavItem(screen)
}

private val DashboardNavItems = listOf(
    DashboardNavItem.Item(
        screen = DashboardNavigationScreen.Home,
        iconRes = R.drawable.ic_home,
        selectedIconRes = R.drawable.ic_home_selected,
    ),
    DashboardNavItem.Item(
        screen = DashboardNavigationScreen.Order,
        iconRes = R.drawable.ic_order,
        selectedIconRes = R.drawable.ic_order_selected,
    ),
    DashboardNavItem.Item(
        screen = DashboardNavigationScreen.Profile,
        iconRes = R.drawable.ic_profile,
        selectedIconRes = R.drawable.ic_profile_selected,
    ),
)

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<DashboardNavigationScreen> {
    val selectedItem = remember { mutableStateOf<DashboardNavigationScreen>(DashboardNavigationScreen.Home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == DashboardNavigationScreen.Order.route } -> {
                    selectedItem.value = DashboardNavigationScreen.Order
                }

                destination.hierarchy.any { it.route == DashboardNavigationScreen.Profile.route } -> {
                    selectedItem.value = DashboardNavigationScreen.Profile
                }

                destination.hierarchy.any { it.route == DashboardNavigationScreen.Home.route } -> {
                    selectedItem.value = DashboardNavigationScreen.Home
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}