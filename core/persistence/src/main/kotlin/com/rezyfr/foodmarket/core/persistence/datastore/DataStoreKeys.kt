package com.rezyfr.foodmarket.core.persistence.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreKeys @Inject constructor() {
    val token by lazy { stringPreferencesKey(name = "token") }
    val userEmail by lazy { stringPreferencesKey(name = "user_email") }
    val userId by lazy { stringPreferencesKey(name = "user_id") }
    val userName by lazy { stringPreferencesKey(name = "user_name") }
    val userPhoto by lazy { stringPreferencesKey(name = "user_photo") }
}