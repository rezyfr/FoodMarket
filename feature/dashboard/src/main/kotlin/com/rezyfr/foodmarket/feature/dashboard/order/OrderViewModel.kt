package com.rezyfr.foodmarket.feature.dashboard.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.order.usecase.GetFoodOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getFoodOrderUseCase: GetFoodOrderUseCase
) : ViewModel() {
    private var onGoingResult = MutableStateFlow(initialUi.onGoingOrders)
    private var pastOrderResult = MutableStateFlow(initialUi.pastOrders)
    private val initialUi: OrderViewState
        get() = OrderViewState()
    private val uiFlow: Flow<OrderViewState>
        get() = combine(onGoingResult, pastOrderResult) { ongoing, past ->
            OrderViewState(
                onGoingOrders = ongoing,
                pastOrders = past
            )
        }
    val uiState: StateFlow<OrderViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
            )
    }

    init {
        getFoodOrder("ongoing")
        getFoodOrder("past")
    }

    private fun getFoodOrder(type: String) {
        viewModelScope.launch {
            getFoodOrderUseCase.invoke(GetFoodOrderUseCase.Params(type)).collectLatest {
                it.fold(
                    { error ->
                        if (type == "past") pastOrderResult.value = ViewResult.Error(error)
                        else onGoingResult.value = ViewResult.Error(error)
                    },
                    { data ->
                        if (type == "past") pastOrderResult.value = ViewResult.Success(data)
                        else onGoingResult.value = ViewResult.Success(data)
                    }
                )
            }
        }
    }
}