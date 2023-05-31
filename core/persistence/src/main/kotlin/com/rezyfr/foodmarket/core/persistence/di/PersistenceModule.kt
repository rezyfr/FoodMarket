package com.rezyfr.foodmarket.core.persistence.di

import com.rezyfr.foodmarket.core.persistence.datastore.DataStoreKeys
import com.rezyfr.foodmarket.core.persistence.datastore.FmDataStore
import com.rezyfr.foodmarket.core.persistence.source.DataStoreSource
import com.rezyfr.foodmarket.core.persistence.source.DataStoreSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    @Singleton
    fun provideDataStoreSource(
        dataStore: FmDataStore,
        dataStoreKeys: DataStoreKeys
    ): DataStoreSource {
        return DataStoreSourceImpl(dataStore, dataStoreKeys)
    }
}
