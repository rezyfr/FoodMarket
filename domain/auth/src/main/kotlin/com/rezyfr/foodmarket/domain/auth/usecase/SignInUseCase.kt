package com.rezyfr.foodmarket.domain.auth.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import javax.inject.Inject

class SignInUseCase @Inject constructor() : UseCase<SignInParams, SignInResult> {
    override suspend fun invoke(params: SignInParams): Either<ViewError, SignInResult> {
        val result = SignInResult(Unit)
        return Either.Right(result)
    }
}