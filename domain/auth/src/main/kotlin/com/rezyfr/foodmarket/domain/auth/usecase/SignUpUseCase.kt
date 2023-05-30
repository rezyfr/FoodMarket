package com.rezyfr.foodmarket.domain.auth.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<SignUpParams, SignUpResult>() {
    override fun execute (params: SignUpParams): Flow<Either<ViewError, SignUpResult>> = flow {
        val result = Either.Right(authRepository.register(params))
        emit(result)
    }
}