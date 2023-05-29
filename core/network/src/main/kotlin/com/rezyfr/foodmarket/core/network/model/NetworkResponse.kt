package com.rezyfr.foodmarket.core.network.model

import okhttp3.Headers
import java.io.IOException
import java.net.HttpURLConnection
import kotlin.jvm.Throws

sealed class NetworkResponse<out T, out U> {
    /**
     * A request that resulted in a response with a 2xx status code that has a body.
     */
    data class Success<T>(
        val body: T,
        val headers: Headers? = null,
        val code: Int
    ) : NetworkResponse<T, Nothing>() {
        companion object {
            fun <T> getDummy(body: T) = Success(body = body, headers = null, code = 0)
        }
    }

    /**
     * A request that resulted in a response with a non-2xx status code.
     */
    data class ServerError<U>(
        val body: U?,
        val code: Int,
        val headers: Headers? = null
    ) : NetworkResponse<Nothing, U>()

    /**
     * A request that didn't result in a response.
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * A request that resulted in an error different from an IO or Server error.
     *
     * An example of such an error is JSON parsing exception thrown by a serialization library.
     */
    data class UnknownError(
        val error: Throwable,
        val code: Int? = null,
        val headers: Headers? = null
    ) : NetworkResponse<Nothing, Nothing>()
}

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