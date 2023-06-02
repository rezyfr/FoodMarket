package com.rezyfr.foodmarket.feature.dashboard.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun FMTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onSurface,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(3.dp)
                    .width(10.dp)
                    .padding(horizontal = 28.dp)
                    .clip(RoundedCornerShape(50.dp)) // clip modifier not working
                    .background(color = MaterialTheme.colors.onSurface)
            )
        },
        tabs = tabs
    )
}
@Composable
fun FMTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .wrapContentWidth(),
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewTab() {
    FoodMarketTheme {
        FMTabRow(
            selectedTabIndex = 0,
            modifier = Modifier,
        ) {
            listOf("asdasdsad", "zxca", "qweqqe").forEachIndexed { index, title ->
                FMTab(
                    selected = index == 0,
                    onClick = {},
                    text = title
                )
            }
        }
    }
}