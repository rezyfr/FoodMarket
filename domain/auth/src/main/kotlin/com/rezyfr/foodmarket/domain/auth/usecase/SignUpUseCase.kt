package com.rezyfr.foodmarket.domain.auth.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import javax.inject.Inject

class SignUpUseCase @Inject constructor() : UseCase<SignUpParams, SignUpResult> {
    override suspend fun invoke(params: SignUpParams): Either<ViewError, SignUpResult> {
        val result = SignUpResult(Unit)
        return Either.Right(result)
    }
}