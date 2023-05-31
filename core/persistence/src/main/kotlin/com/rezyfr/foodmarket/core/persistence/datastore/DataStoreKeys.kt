package com.rezyfr.foodmarket.core.persistence.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreKeys @Inject constructor() {
    val token by lazy { stringPreferencesKey(name = "token") }
}