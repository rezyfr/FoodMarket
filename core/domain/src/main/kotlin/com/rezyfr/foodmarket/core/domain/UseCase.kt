package com.rezyfr.foodmarket.core.domain

import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class UseCase<in Params, out Result> where Result : Any {
    suspend operator fun invoke(params: Params): Flow<Either<ViewError, Result>> = execute(params).catch {
            emit(Either.Left(ViewError(it.message)))
        }

    protected abstract fun execute(params: Params): Flow<Either<ViewError, Result>>
}