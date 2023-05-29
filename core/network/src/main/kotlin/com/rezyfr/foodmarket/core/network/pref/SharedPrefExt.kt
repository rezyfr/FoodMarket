package com.rezyfr.foodmarket.core.network.pref

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

@Suppress("UNCHECKED_CAST")
fun <T> SharedPreferences.get(
    key: String,
    defaultValue: T,
    clazz: Class<T>? = null
): T {
    return when (defaultValue) {
        is Boolean -> this.getBoolean(key, defaultValue as Boolean) as T
        is Float -> this.getFloat(key, defaultValue as Float) as T
        is Int -> this.getInt(key, defaultValue as Int) as T
        is Long -> this.getLong(key, defaultValue as Long) as T
        is String -> this.getString(key, defaultValue as String) as T
        is Set<*> -> this.getStringSet(key, defaultValue as Set<String>) as T
        else -> {
            clazz?.let {
                this.getParcelable(key, defaultValue, it)
            } ?: defaultValue
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> SharedPreferences.put(
    key: String,
    value: T,
    clazz: Class<T>? = null
) {
    val editor = this.edit()
    when (value) {
        is Boolean -> editor.putBoolean(key, value as Boolean)
        is Float -> editor.putFloat(key, value as Float)
        is Int -> editor.putInt(key, value as Int)
        is Long -> editor.putLong(key, value as Long)
        is String -> editor.putString(key, value as String)
        is Set<*> -> editor.putStringSet(key, value as Set<String>)
        else ->
            clazz?.let {
                editor.putParcelable(key, value, clazz)
            }
    }
    editor.apply()
}

private fun <T> SharedPreferences.getParcelable(key: String, defValue: T, clazz: Class<T>): T {
    return try {
        val json = this.getString(key, "")
        Gson().fromJson(json, clazz)
    } catch (ex: JsonSyntaxException) {
        defValue
    }
}

private fun <T> SharedPreferences.Editor.putParcelable(key: String, value: T, clazz: Class<T>) {
    this.putString(key, Gson().toJson(value, clazz))
}