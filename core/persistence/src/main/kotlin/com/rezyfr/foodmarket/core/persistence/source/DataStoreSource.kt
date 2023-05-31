package com.rezyfr.foodmarket.core.persistence.source

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
    fun getToken(): Flow<String?>
    suspend fun saveToken(token: String)
}