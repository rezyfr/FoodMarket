package com.rezyfr.foodmarket.feature.order.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.core.ui.component.FMHeader
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithBackButton
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.order.R
import com.rezyfr.foodmarket.feature.order.payment.component.FoodOrderItem

@Composable
fun PaymentScreen(
    navigateUp: () -> Unit = {},
    openOngoingOrder: () -> Unit = {},
    paymentParams: PaymentParams
) {
    Payment(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
        openOngoingOrder = openOngoingOrder,
        paymentParams = paymentParams
    )
}
@Composable
fun Payment(
    viewModel: PaymentViewModel,
    navigateUp: () -> Unit = {},
    openOngoingOrder: () -> Unit = {},
    paymentParams: PaymentParams
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.onEvent(PaymentViewEvent.InitPaymentParams(paymentParams))

    PaymentContent(
        state = viewState,
        navigateUp = navigateUp,
        openOngoingOrder = openOngoingOrder
    )
}
@Composable
fun PaymentContent(
    state: PaymentViewState,
    navigateUp: () -> Unit = {},
    openOngoingOrder: () -> Unit = {}
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        FMHeaderWithBackButton(
            headerText = stringResource(id = R.string.lbl_payment),
            subtitleText = stringResource(id = R.string.lbl_payment_subtitle),
            onBackClicked = navigateUp
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        PaymentBody(
            Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            state = state
        )
    }
}
@Composable
fun PaymentBody(modifier: Modifier = Modifier, state: PaymentViewState) {
    Column(modifier) {
        Text(
            text = stringResource(id = R.string.lbl_ordered_item),
            style = MaterialTheme.typography.body1
        )
        VSpacer(12)
        FoodOrderItem(params = state.params, name = state.food.first, image = state.food.second)
    }
}

