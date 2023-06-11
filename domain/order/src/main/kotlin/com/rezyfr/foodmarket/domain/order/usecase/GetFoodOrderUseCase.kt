package com.rezyfr.foodmarket.domain.order.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.order.model.FoodOrderModel
import com.rezyfr.foodmarket.domain.order.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) : UseCase<GetFoodOrderUseCase.Params, List<FoodOrderModel>>() {
    override fun execute(params: Params): Flow<Either<ViewError, List<FoodOrderModel>>> = flow {
        val data = repository.getFoodOrder(params.orderState)
        emit(Either.Right(data))
    }

    data class Params(
        val orderState: String
    )
}
