package com.rezyfr.foodmarket.feature.order.food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.usecase.GetFoodByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodByIdUseCase: GetFoodByIdUseCase
) : BaseFlowViewModel<FoodDetailViewState, FoodDetailEvent>() {


    private var food = MutableStateFlow(initialUi.food)
    private var order = MutableStateFlow(initialUi.orderParams)
    init {
        getFoodById()
    }

    private fun getFoodById() {
        viewModelScope.launch {
            val foodId: String = savedStateHandle["foodId"]!!
            getFoodByIdUseCase.invoke(foodId).collectLatest {
                it.fold(
                    ifRight = { food.value = ViewResult.Success(it) },
                    ifLeft = { food.value = ViewResult.Error(it) }
                )
            }
        }
    }
    override val initialUi: FoodDetailViewState
        get() = FoodDetailViewState()
    override val uiFlow: Flow<FoodDetailViewState>
        get() = combine(food, order) { food, order ->
            if (food is ViewResult.Success) {
                FoodDetailViewState(food = food, orderParams = OrderParams(food.data.id, order.quantity, order.total))
            } else {
                FoodDetailViewState()
            }
        }

    override suspend fun handleEvent(event: FoodDetailEvent) {
        when (event) {
            is FoodDetailEvent.ChangeQty -> {
                val currentQty = order.value.quantity
                if (!event.isAdd && currentQty == 0) return

                val newQty = if (event.isAdd) currentQty + 1 else currentQty - 1
                val newTotal = newQty * (food.value as ViewResult.Success).data.price
                order.value = order.value.copy(quantity = newQty, total = newTotal.toDouble())
            }
        }
    }
}

sealed interface FoodDetailEvent {
    data class ChangeQty(val isAdd: Boolean) : FoodDetailEvent
}

data class FoodDetailViewState(
    val food: ViewResult<FoodModel> = ViewResult.Uninitialized,
    val orderParams: OrderParams = OrderParams("", 0, 0.0)
)

data class OrderParams(
    val foodId: String,
    val quantity: Int,
    val total: Double,
)