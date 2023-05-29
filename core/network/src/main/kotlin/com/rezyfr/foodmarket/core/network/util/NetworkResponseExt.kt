package com.rezyfr.foodmarket.core.network.util

import com.rezyfr.foodmarket.core.domain.model.CustomException
import com.rezyfr.foodmarket.core.domain.model.ErrorResponse
import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import java.net.HttpURLConnection

@Throws(CustomException::class)
fun <T> NetworkResponse<T, ErrorResponse>.handleResponse(): T {
    return when (this) {
        is NetworkResponse.Success -> this.body
        is NetworkResponse.ServerError -> throw CustomException(
            code = this.code,
            message = this.body?.message.orEmpty()
        )

        is NetworkResponse.NetworkError -> throw CustomException(
            code = HttpURLConnection.HTTP_INTERNAL_ERROR,
            message = this.error.message.orEmpty()
        )

        is NetworkResponse.UnknownError -> throw CustomException(
            code = 520, // 520 == Unknown Error
            message = this.error.message.orEmpty()
        )
    }
}