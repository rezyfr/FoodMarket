package com.rezyfr.foodmarket.feature.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.FMHeader
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithTrailingImage
import okhttp3.internal.http2.Header

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Home(
        viewModel = hiltViewModel()
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Home(
    viewModel: HomeViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        state = viewState
    )
}
@Composable
fun HomeContent(state: HomeViewState) {
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Header(Modifier, state)
    }
}
@Composable
fun Header(
    modifier: Modifier = Modifier,
    state: HomeViewState
) {
    FMHeaderWithTrailingImage(
        headerText = "FoodMarket",
        subtitleText = "Let's get some foods",
    ) {
        val url = if(state.profile is ViewResult.Success) state.profile.data.profilePhotoPath ?: state.profile.data.profilePhotoUrl
        else ""
        AsyncImage(
            modifier = modifier
                .size(50.dp)
                .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp)),
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}