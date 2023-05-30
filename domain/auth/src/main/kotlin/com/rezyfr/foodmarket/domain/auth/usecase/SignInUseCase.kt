package com.rezyfr.foodmarket.domain.auth.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor() : UseCase<SignInParams, SignInResult>() {
    override fun execute(params: SignInParams): Flow<Either<ViewError, SignInResult>> = flow {
        val result = SignInResult(Unit)
        emit(Either.Right(result))
    }
}