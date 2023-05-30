package com.rezyfr.foodmarket.core.network.pref

import android.content.SharedPreferences

class SharedPrefEntry<T>(
    private val pref: SharedPreferences,
    private val key: String,
    private val defaultValue: T,
    private val clazz: Class<T>? = null
) {
    init {
        if (!isBasicType(defaultValue) && clazz == null) {
            throw IllegalArgumentException("class is required for custom object")
        }
    }

    fun get() = pref.get(key, defaultValue, clazz)
    fun set(value: T) = pref.put(key, value, clazz)
    fun exist() = pref.contains(key)
    fun reset() = pref.put(key, defaultValue, clazz)
    fun remove() = pref.edit().remove(key).apply()
}

private fun <T> isBasicType(value: T): Boolean {
    return value is Boolean || value is Int || value is Long ||
            value is String || value is Float || value is Set<*>
}