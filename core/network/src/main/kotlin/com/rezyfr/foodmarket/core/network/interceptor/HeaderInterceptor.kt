package com.rezyfr.foodmarket.core.network.interceptor

import com.rezyfr.foodmarket.core.persistence.source.DataStoreSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val ds: DataStoreSource) : Interceptor {
    private var token: String = ""
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        CoroutineScope(Dispatchers.Default).launch {
            token = ds.getToken().first().orEmpty()
        }

        val response = if (token.isBlank()) {
            chain.proceed(originalRequest)
        } else {
            // add access token header
            val authRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authRequest)
        }

        // handle expired session
        if (response.code == 401) {
            // Should logout
        }

        return response
    }
}