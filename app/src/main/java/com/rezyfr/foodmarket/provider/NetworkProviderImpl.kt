package com.rezyfr.foodmarket.provider

import com.rezyfr.foodmarket.core.network.NetworkProvider

class NetworkProviderImpl : NetworkProvider {
    override val baseUrl: String
        get() = BuildConfig.BASE_URL
}