package com.rezyfr.foodmarket.feature.order.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithBackButton
import com.rezyfr.foodmarket.core.ui.component.PrimaryButton
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.order.model.PaymentDetails
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.order.R
import com.rezyfr.foodmarket.feature.order.payment.component.FoodOrderItem
import com.rezyfr.foodmarket.feature.order.payment.component.LabelValueItem

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
            Modifier.padding(vertical = 16.dp),
            state = state,
            openOngoingOrder = openOngoingOrder
        )
    }
}
@Composable
fun PaymentBody(
    modifier: Modifier = Modifier,
    state: PaymentViewState,
    openOngoingOrder: () -> Unit = {}
) {
    Column(
        Modifier.verticalScroll(rememberScrollState())) {
        ItemOrdered(modifier = Modifier.padding(horizontal = 16.dp), state = state)
        VSpacer(16)
        DetailTransaction(modifier = Modifier.padding(horizontal = 16.dp), state = state)
        VSpacer(16)
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        VSpacer(16)
        DeliverData(modifier = Modifier.padding(horizontal = 16.dp), state = state)
        CheckoutButton(
            modifier = Modifier
                .fillMaxWidth(),
            state = state,
            onClick = openOngoingOrder
        )
    }
}
@Composable
fun CheckoutButton(
    modifier: Modifier = Modifier,
    state: PaymentViewState,
    onClick: () -> Unit = {}
) {
    Box(Modifier.padding(24.dp)) {
        PrimaryButton(
            modifier = modifier,
            text = "Checkout Now"
        ) {
            onClick()
        }
    }
}
@Composable
fun DeliverData(
    modifier: Modifier = Modifier, state: PaymentViewState
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.lbl_deliver_to),
            style = MaterialTheme.typography.body2
        )
        VSpacer(16)
        LabelValueItem(stringResource(id = R.string.lbl_name), state.params.total.formatCurrency())
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_phone_no),
            state.details.driverFee.formatCurrency()
        )
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_address),
            state.details.tax.formatCurrency()
        )
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_house_no),
            state.details.totalAmount.formatCurrency(),
            valueStyle = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Medium
            )
        )
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_city),
            state.details.totalAmount.formatCurrency(),
            valueStyle = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
@Composable
fun DetailTransaction(
    modifier: Modifier = Modifier, state: PaymentViewState
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.lbl_detail_transaction),
            style = MaterialTheme.typography.body2
        )
        VSpacer(16)
        LabelValueItem(state.food.first, state.params.total.formatCurrency())
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_driver),
            state.details.driverFee.formatCurrency()
        )
        LabelValueItem(stringResource(id = R.string.lbl_tax), state.details.tax.formatCurrency())
        VSpacer(6)
        LabelValueItem(
            stringResource(id = R.string.lbl_total_price),
            state.details.totalAmount.formatCurrency(),
            valueStyle = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
@Composable
fun ItemOrdered(
    modifier: Modifier = Modifier,
    state: PaymentViewState
) {
    Column(modifier = modifier.padding(top = 16.dp)) {
        Text(
            text = stringResource(id = R.string.lbl_ordered_item),
            style = MaterialTheme.typography.body2
        )
        VSpacer(12)
        FoodOrderItem(params = state.params, name = state.food.first, image = state.food.second)
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewPaymentScreen() {
    FoodMarketTheme {
        PaymentContent(
            state = PaymentViewState(
                params = PaymentParams(
                    total = 150000,
                    foodId = 1,
                    quantity = 5,
                    status = "",
                    foodPrice = 30000,
                    userId = ""
                ),
                food = Pair("Cherry Healthy", ""),
                details = PaymentDetails(
                    tax = 15000,
                    totalAmount = 215000,
                    driverFee = 50000
                )
            )
        )
    }
}