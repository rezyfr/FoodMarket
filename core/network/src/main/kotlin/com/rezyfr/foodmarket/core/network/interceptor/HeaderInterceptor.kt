package com.rezyfr.foodmarket.core.network.interceptor

import com.rezyfr.foodmarket.core.network.pref.CommonPref
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor(private val pref: CommonPref) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = pref.token.get()

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