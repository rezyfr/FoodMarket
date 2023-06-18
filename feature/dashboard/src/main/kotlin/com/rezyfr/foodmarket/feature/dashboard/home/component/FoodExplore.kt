package com.rezyfr.foodmarket.feature.dashboard.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.feature.dashboard.home.HomeViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodExplore(
    modifier: Modifier = Modifier,
    openFoodDetail: (foodId: String) -> Unit = { },
    state: HomeViewState
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    FoodExploreContent(
        modifier = modifier,
        state = state,
        openFoodDetail = openFoodDetail,
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        selectedTab = pagerState.currentPage
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodExploreContent(
    modifier: Modifier = Modifier,
    state: HomeViewState = HomeViewState(),
    openFoodDetail: (foodId: String) -> Unit = { },
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    selectedTab: Int = 0
) {
    Column(modifier) {
        Box {
            Box(
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(1.dp)
                    .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.5f))
            )
            FMTabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                listOf("New Taste", "Popular", "Recommended").forEachIndexed { index, title ->
                    FMTab(
                        selected = selectedTab == index,
                        onClick = {
                            coroutineScope.launch { pagerState.animateScrollToPage(index) }
                        },
                        text = title
                    )
                }
            }
        }
        HorizontalPager(pageCount = 3, state = pagerState) { page ->
            when (page) {
                0 -> FoodExploreList(state = state, openFoodDetail = openFoodDetail)
                1 -> FoodExploreList(state = state, openFoodDetail = openFoodDetail)
                2 -> FoodExploreList(state = state, openFoodDetail = openFoodDetail)
            }
        }
    }
}
@Composable
fun FoodExploreList(
    modifier: Modifier = Modifier,
    openFoodDetail: (foodId: String) -> Unit = { },
    state: HomeViewState
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        when (state.foods) {
            is ViewResult.Success -> {
                itemsIndexed(state.foods.data) { index, food ->
                    if (index == 0) VSpacer(16)
                    FoodExploreItem(
                        food = food,
                        modifier = Modifier.clickable { openFoodDetail(food.id) }
                    )
                    if (index == state.foods.data.lastIndex) VSpacer(16)
                }
            }

            else -> Unit
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun FoodExplorePreview() {
    FoodMarketTheme {
        FoodExplore(
            state = HomeViewState(
                foods = ViewResult.Success(FoodModel.getDummy())
            )
        )
    }
}