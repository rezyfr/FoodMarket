package com.rezyfr.foodmarket.feature.order.payment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.domain.order.model.PaymentDetails
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.domain.order.usecase.GetPaymentDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPaymentDetailsUseCase: GetPaymentDetailsUseCase
) : ViewModel() {
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

    private val initialUi: PaymentViewState
        get() = PaymentViewState()
    private val uiFlow: Flow<PaymentViewState>
        get() = combine(paymentParams, paymentResult, food, paymentDetails) { params, result, food, paymentDetails ->
            getPaymentDetails(params)
            PaymentViewState(
                params = params,
                result = result,
                food = food,
                details = paymentDetails
            )
        }
    val uiState: StateFlow<PaymentViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
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

    fun onEvent(event: PaymentViewEvent) {
        when(event){
            is PaymentViewEvent.InitPaymentParams -> paymentParams.value = event.params
            PaymentViewEvent.OnOrderClicked -> Unit
        }
    }
}