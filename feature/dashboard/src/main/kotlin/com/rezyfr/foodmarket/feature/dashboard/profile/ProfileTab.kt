package com.rezyfr.foodmarket.feature.dashboard.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
fun ProfileTab(
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ProfileTabContent(
        modifier = modifier,
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        selectedTab = pagerState.currentPage
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileTabContent(
    modifier: Modifier = Modifier,
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
                listOf("Account", "FoodMarket").forEachIndexed { index, title ->
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
        HorizontalPager(pageCount = 2, state = pagerState) { page ->
            when (page) {
                0 -> AccountTab()
                1 -> FoodMarketTab()
            }
        }
    }
}

@Composable
private fun AccountTab() {
    Text(text = "Account Tab")
}

@Composable
private fun FoodMarketTab() {
    Text(text = "Food Market Tab")
}