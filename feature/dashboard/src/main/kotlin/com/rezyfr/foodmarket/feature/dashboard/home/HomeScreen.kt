package com.rezyfr.foodmarket.feature.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rezyfr.foodmarket.core.domain.model.PagingState
import com.rezyfr.foodmarket.core.domain.model.PagingState.*
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithTrailingImage
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.feature.dashboard.home.component.FoodCarouselItem
import com.rezyfr.foodmarket.feature.dashboard.home.component.FoodExplore

@Composable
fun HomeScreen(
    openFoodDetail: (foodId: String) -> Unit = { }
) {
    Home(
        viewModel = hiltViewModel(),
        openFoodDetail = openFoodDetail
    )
}
@Composable
fun Home(
    viewModel: HomeViewModel,
    openFoodDetail: (foodId: String) -> Unit = { }
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyColumnListState = rememberLazyListState()
    val shouldPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.last().index) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldPaginate.value) {
        if (shouldPaginate.value && viewModel.pagingState == IDLE) {
            viewModel.getFoodExplore()
        }
    }

    HomeContent(
        state = viewState,
        pagingState = viewModel.pagingState,
        openFoodDetail = openFoodDetail
    )
}
@Composable
fun HomeContent(
    state: HomeViewState,
    pagingState: PagingState = IDLE,
    openFoodDetail: (foodId: String) -> Unit = { }
) {
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Header(Modifier, state)
        FoodCarousel(Modifier, openFoodDetail, state, pagingState)
        FoodExplore(Modifier, openFoodDetail, state)
    }
}
@Composable
fun FoodCarousel(
    modifier: Modifier = Modifier,
    openFoodDetail: (foodId: String) -> Unit = { },
    state: HomeViewState,
    pagingState: PagingState
) {
    LazyRow(
        modifier
            .background(MaterialTheme.colors.secondary.copy(alpha = 0.1f))
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(state.foods) { index, food ->
            if (index == 0) HSpacer(16)
            FoodCarouselItem(
                food = food,
                modifier = Modifier.clickable { openFoodDetail(food.id) }
            )
            if (index == state.foods.lastIndex) HSpacer(16)
        }
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
        val url = if (state.profile is ViewResult.Success) state.profile.data.profilePhotoPath
            ?: state.profile.data.profilePhotoUrl
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
@SuppressLint("UnrememberedMutableState")
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHomeContent() {
    FoodMarketTheme {
        HomeContent(
            state = HomeViewState(
                profile = ViewResult.Success(
                    UserDomainModel(
                        id = 1,
                        name = "Rezyfr",
                        email = "",
                        profilePhotoUrl = ""
                    ),
                ),
                foods = mutableStateListOf<FoodModel>().apply {
                    addAll(FoodModel.getDummy())
                }
            )
        )
    }
}