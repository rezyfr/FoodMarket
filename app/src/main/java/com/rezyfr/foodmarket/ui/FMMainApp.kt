package com.rezyfr.foodmarket.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rezyfr.foodmarket.navigation.AuthNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FMMainApp(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isUserHaveToken by mainViewModel.tokenState.collectAsState()
    Crossfade(targetState = isUserHaveToken, label = "MainApp") { state ->
        when (state) {
            TokenState.INITIAL -> Unit
            TokenState.LOGGED_VALID -> DashboardScreen()
            else -> AuthNavigation()
        }
    }
}