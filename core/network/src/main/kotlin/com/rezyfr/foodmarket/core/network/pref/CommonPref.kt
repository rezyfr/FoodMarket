package com.rezyfr.foodmarket.core.network.pref

import android.content.Context

class CommonPref(context: Context) {
    private val pref by lazy {
        context.getSharedPreferences("foodmarket", Context.MODE_PRIVATE)
    }
    val token by lazy { SharedPrefEntry(pref, "token", "") }
}