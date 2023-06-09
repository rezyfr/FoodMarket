package com.rezyfr.foodmarket.core.persistence.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FmDataStore @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "fm_datastore"
    )

    val dataStore: DataStore<Preferences> = appContext.dataStore
    suspend fun <T> put(
        key: Preferences.Key<T>,
        value: T
    ) {
        appContext.dataStore.edit {
            it[key] = value
        }
    }
    suspend fun <T> remove(key: Preferences.Key<T>) {
        appContext.dataStore.edit {
            it.remove(key = key)
        }
    }

    fun <T> get(key: Preferences.Key<T>): Flow<T?> = appContext.dataStore.data.map { it[key] }
}