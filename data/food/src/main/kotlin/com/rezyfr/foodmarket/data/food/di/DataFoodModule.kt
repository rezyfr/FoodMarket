package com.rezyfr.foodmarket.data.food.di

import com.rezyfr.foodmarket.data.food.FoodService
import com.rezyfr.foodmarket.data.food.repository.FoodRepositoryImpl
import com.rezyfr.foodmarket.domain.food.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataFoodModule {
    @Singleton
    @Provides
    fun provideFoodRepository(
        foodService: FoodService
    ): FoodRepository {
        return FoodRepositoryImpl(foodService)
    }
    @Singleton
    @Provides
    fun provideFoodService(
        retrofit: Retrofit
    ): FoodService {
        return retrofit.create(FoodService::class.java)
    }
}