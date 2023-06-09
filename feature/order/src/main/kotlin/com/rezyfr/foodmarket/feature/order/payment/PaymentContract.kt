package com.rezyfr.foodmarket.feature.order.payment

import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.order.model.PaymentDetails
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.domain.order.model.PaymentResult

data class PaymentViewState(
    val params: PaymentParams = PaymentParams.empty(),
    val result: ViewResult<PaymentResult> = ViewResult.Uninitialized,
    val food: Pair<String, String> = Pair("", ""),
    val details: PaymentDetails = PaymentDetails()
)


sealed interface PaymentViewEvent {
    data class InitPaymentParams(val params: PaymentParams) : PaymentViewEvent
    object OnOrderClicked : PaymentViewEvent
}
