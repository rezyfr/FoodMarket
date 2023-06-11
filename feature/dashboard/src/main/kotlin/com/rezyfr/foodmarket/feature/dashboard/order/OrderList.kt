package com.rezyfr.foodmarket.feature.dashboard.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.feature.dashboard.home.component.FMTab
import com.rezyfr.foodmarket.feature.dashboard.home.component.FMTabRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    state: OrderViewState,
    openPayment: (orderId: String) -> Unit = { }
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    OrderListContent(
        modifier = modifier,
        state = state,
        openPayment = openPayment,
        pagerState = pagerState,
        coroutineScope = coroutineScope
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderListContent(
    modifier: Modifier = Modifier,
    state: OrderViewState = OrderViewState(),
    openPayment: (orderId: String) -> Unit = {},
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
                listOf("In Progress", "Past Orders").forEachIndexed { index, title ->
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
                0 -> FoodOrderList(type = "ongoing", state = state, openPayment = openPayment)
                1 -> FoodOrderList(type = "past", state = state, openPayment = openPayment)
            }
        }
    }
}
@Composable
fun FoodOrderList(
    modifier: Modifier = Modifier,
    openPayment: (orderId: String) -> Unit = { },
    type: String,
    state: OrderViewState
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        if (type == "ongoing") {
            foodOrderItem(foodOrderResult = state.onGoingOrders, openPayment = openPayment)
        } else {
            foodOrderItem(foodOrderResult = state.pastOrders, openPayment = openPayment)
        }
    }
}