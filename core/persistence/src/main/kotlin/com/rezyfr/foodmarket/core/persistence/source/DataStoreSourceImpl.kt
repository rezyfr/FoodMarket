package com.rezyfr.foodmarket.core.persistence.source

import com.rezyfr.foodmarket.core.persistence.datastore.DataStoreKeys
import com.rezyfr.foodmarket.core.persistence.datastore.FmDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreSourceImpl @Inject constructor(
    private val dataStore: FmDataStore,
    private val dataStoreKeys: DataStoreKeys
): DataStoreSource {
    override fun getToken(): Flow<String?> = dataStore.get(dataStoreKeys.token)
    override suspend fun saveToken(token: String) = dataStore.put(dataStoreKeys.token, token)
}