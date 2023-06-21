package com.rezyfr.foodmarket.domain.food.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.PagingResult
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetAllFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) : UseCase<Int, PagingResult<FoodModel>>() {
    override fun execute(params: Int): Flow<Either<ViewError, PagingResult<FoodModel>>> = channelFlow {
        foodRepository.getFoods(params).collectLatest {
            trySend(Either.Right(it))
        }
    }
}