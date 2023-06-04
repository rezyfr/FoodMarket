package com.rezyfr.foodmarket.feature.order.payment

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.order.model.PaymentDetails
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.domain.order.model.PaymentResult
import com.rezyfr.foodmarket.domain.order.usecase.GetPaymentDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPaymentDetailsUseCase: GetPaymentDetailsUseCase
) : BaseFlowViewModel<PaymentViewState, PaymentViewEvent>() {
    private val paymentResult = MutableStateFlow(initialUi.result)
    private val paymentParams: MutableStateFlow<PaymentParams> = MutableStateFlow(initialUi.params)
    private val paymentDetails: MutableStateFlow<PaymentDetails> = MutableStateFlow(initialUi.details)
    private val food = MutableStateFlow(initialUi.food)

    init {
        getInitialData()
    }

    private fun getInitialData() {
        val foodName: String = savedStateHandle["food_name"]!!
        food.value = Pair(foodName, "https://static.wikia.nocookie.net/gensin-impact/images/8/8f/Item_Satisfying_Salad.png/revision/latest?cb=20210417153219")
    }

    override val initialUi: PaymentViewState
        get() = PaymentViewState()
    override val uiFlow: Flow<PaymentViewState>
        get() = combine(paymentParams, paymentResult, food, paymentDetails) { params, result, food, paymentDetails ->
            getPaymentDetails(params)
            PaymentViewState(
                params = params,
                result = result,
                food = food,
                details = paymentDetails
            )
        }

    private fun getPaymentDetails(params: PaymentParams) {
        if(params.foodId.isNotEmpty()) {
            viewModelScope.launch {
                getPaymentDetailsUseCase.invoke(params).collect {
                    it.fold(
                        { _ -> }, // Handle Error,
                        { data -> paymentDetails.value = data }
                    )
                }
            }
        }
    }

    override suspend fun handleEvent(event: PaymentViewEvent) {
        when(event){
            is PaymentViewEvent.InitPaymentParams -> paymentParams.value = event.params
            PaymentViewEvent.OnOrderClicked -> Unit
        }
    }
}

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
