package com.rezyfr.foodmarket.feature.order.food

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.domain.auth.usecase.GetUserProfileUseCase
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.usecase.GetFoodByIdUseCase
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.order.model.OrderParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodByIdUseCase: GetFoodByIdUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val food = MutableStateFlow(initialUi.food)
    private val order = MutableStateFlow(initialUi.orderParams)
    private val _effect: Channel<FoodDetailViewEffect> = Channel()
    val effect = _effect.receiveAsFlow()
    private val initialUi: FoodDetailViewState
        get() = FoodDetailViewState()
    private val uiFlow: Flow<FoodDetailViewState>
        get() = combine(food, order) { food, order ->
            if (food is ViewResult.Success) {
                FoodDetailViewState(
                    food = food,
                    orderParams = OrderParams(food.data.id, order.quantity, order.total)
                )
            } else {
                FoodDetailViewState()
            }
        }

    val uiState: StateFlow<FoodDetailViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = FoodDetailViewState(),
            )
    }

    init {
        getFoodById()
    }

    fun onEvent(event: FoodDetailEvent) {
        when (event) {
            is FoodDetailEvent.ChangeQty -> onChangeQty(event)
            FoodDetailEvent.OnOrderClicked -> onOrderClicked()
        }
    }

    private fun getFoodById() {
        viewModelScope.launch {
            val foodId: Int = savedStateHandle["foodId"]!!
            getFoodByIdUseCase.invoke(foodId).collectLatest {
                it.fold(
                    ifRight = { food.value = ViewResult.Success(it) },
                    ifLeft = { food.value = ViewResult.Error(it) }
                )
            }
        }
    }

    private fun onChangeQty(event: FoodDetailEvent.ChangeQty) {
        val currentQty = order.value.quantity
        val food = food.value
        if (!event.isAdd && currentQty == 0) return
        if (food !is ViewResult.Success) return
        val newQty = if (event.isAdd) currentQty + 1 else currentQty - 1
        val newTotal = newQty * food.data.price
        order.value = order.value.copy(quantity = newQty, total = newTotal.toDouble())
    }

    private fun onOrderClicked() {
        val food = food.value
        if (food !is ViewResult.Success) return

        viewModelScope.launch {
            val order = order.value
            val paymentParams = PaymentParams(
                foodId = food.data.id,
                userId = "",
                quantity = order.quantity,
                total = order.total.toInt(),
                foodPrice = food.data.price.toInt(),
                status = "",
            )

            getUserProfileUseCase.invoke(null).collectLatest {
                it.fold(
                    ifRight = { user ->
                        if (user.id != -1) _effect.trySend(
                            FoodDetailViewEffect.OpenPaymentScreen(
                                paymentParams.copy(
                                    userId = user.id.toString(),
                                ),
                                foodName = food.data.name,
                                foodImage = food.data.picture,
                            )
                        )
                    },
                    ifLeft = {
                        // Do Something
                    }
                )
            }
        }
    }
}