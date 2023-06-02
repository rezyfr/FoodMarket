package com.rezyfr.foodmarket.core.ui.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Long.formatCurrency(): String {
    return try {
        val locale = Locale("id", "ID")
        val decimal = DecimalFormatSymbols(locale)

        val formatter = DecimalFormat("#,###", decimal)
        "IDR ${formatter.format(this)}"
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}