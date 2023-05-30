package com.rezyfr.foodmarket.core.domain.model

import java.io.IOException
import java.net.HttpURLConnection
import kotlin.jvm.Throws

sealed class NetworkResponse<out T, out U> {
    /**
     * A request that resulted in a response with a 2xx status code that has a body.
     */
    data class Success<T>(
        val body: T,
        val code: Int
    ) : NetworkResponse<T, Nothing>() {
        companion object {
            fun <T> getDummy(body: T) = Success(body = body, code = 0)
        }
    }

    /**
     * A request that resulted in a response with a non-2xx status code.
     */
    data class ServerError<U>(
        val body: U?,
        val code: Int,
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
    ) : NetworkResponse<Nothing, Nothing>()
}
