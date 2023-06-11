package com.rezyfr.foodmarket.data.order.di

import com.rezyfr.foodmarket.data.order.repository.OrderRepositoryImpl
import com.rezyfr.foodmarket.domain.order.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataOrderModule {
    @Singleton
    @Provides
    fun provideOrderRepository(): OrderRepository {
        return OrderRepositoryImpl()
    }
}