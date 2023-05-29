package com.rezyfr.foodmarket.core.network.di

import android.content.Context
import com.rezyfr.foodmarket.core.network.BuildConfig
import com.rezyfr.foodmarket.core.network.BuildConfig.BASE_URL
import com.rezyfr.foodmarket.core.network.NetworkProvider
import com.rezyfr.foodmarket.core.network.adapter.NetworkResponseAdapterFactory
import com.rezyfr.foodmarket.core.network.interceptor.HeaderInterceptor
import com.rezyfr.foodmarket.core.network.pref.CommonPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        commonPref: CommonPref,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        // uncomment this if you're testing and don't want ok http to cache request or response
        // builder.cache(null)
        builder.addInterceptor(HeaderInterceptor(commonPref))
        // add logging as last interceptor. remember interceptor are sequential
        builder.addInterceptor(interceptor)

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        // you can use the logging level BODY when you want to debug in your local machine
        // but please don't commit it
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return interceptor
    }
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        networkProvider: NetworkProvider
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(networkProvider.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }
}