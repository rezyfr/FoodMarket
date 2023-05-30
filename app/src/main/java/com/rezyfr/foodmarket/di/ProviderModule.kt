package com.rezyfr.foodmarket.di

import com.rezyfr.foodmarket.core.network.NetworkProvider
import com.rezyfr.foodmarket.provider.NetworkProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {
    @Provides
    @Singleton
    fun provideNetworkProvider() : NetworkProvider {
        return NetworkProviderImpl()
    }
}