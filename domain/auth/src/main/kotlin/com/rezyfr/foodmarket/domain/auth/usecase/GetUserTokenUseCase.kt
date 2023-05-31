package com.rezyfr.foodmarket.domain.auth.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.core.domain.utils.right
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Nothing?, String>() {
    override fun execute(params: Nothing?): Flow<Either<ViewError, String>> =
        authRepository.getToken().map {
            it.orEmpty().right()
        }
}