package com.rezyfr.foodmarket.core.network.di

import android.content.Context
import com.rezyfr.foodmarket.core.network.pref.CommonPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideCommonPref(@ApplicationContext context: Context): CommonPref {
        return CommonPref(context)
    }
}