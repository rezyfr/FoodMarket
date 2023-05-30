package com.rezyfr.foodmarket.domain.food.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) : UseCase<Unit, List<FoodModel>>() {
    override fun execute(params: Unit): Flow<Either<ViewError, List<FoodModel>>> = flow {
        val result = Either.Right(foodRepository.getFoods())
        emit(result)
    }
}