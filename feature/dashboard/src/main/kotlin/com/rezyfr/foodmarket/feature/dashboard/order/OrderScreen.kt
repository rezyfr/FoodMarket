package com.rezyfr.foodmarket.feature.dashboard.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.core.ui.component.FMHeader
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    openPayment: (foodId: String) -> Unit = { }
) {
    Order(
        viewModel = hiltViewModel(),
        openPayment = openPayment
    )
}
@Composable
private fun Order(
    viewModel: OrderViewModel,
    openPayment: (foodId: String) -> Unit = { }
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    OrderContent(
        state = viewState,
        openPayment = openPayment
    )
}
@Composable
private fun OrderContent(
    state: OrderViewState,
    openPayment: (orderId: String) -> Unit = { }
) {
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Header(Modifier, state)
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        OrderList(state = state, modifier = Modifier, openPayment = openPayment)
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    state: OrderViewState
) {
    FMHeader(
        headerText = "Your Orders",
        subtitleText = "Wait for the best meal",
    )
}
@Composable
@Preview
private fun OrderScreenPreview() {
    FoodMarketTheme {
        OrderScreen()
    }
}