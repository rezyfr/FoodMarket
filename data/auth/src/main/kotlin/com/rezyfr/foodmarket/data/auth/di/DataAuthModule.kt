package com.rezyfr.foodmarket.data.auth.di

import com.rezyfr.foodmarket.data.auth.AuthService
import com.rezyfr.foodmarket.data.auth.repository.AuthRepositoryImpl
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataAuthModule {
    @Singleton
    @Provides
    fun provideAuthRepository(
        authService: AuthService
    ): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Singleton
    @Provides
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}