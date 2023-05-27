package com.rezyfr.foodmarket.core.domain

import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either

interface UseCase<in Params, out Result> where Result : Any {
    suspend operator fun invoke(params: Params): Either<ViewError, Result>
}