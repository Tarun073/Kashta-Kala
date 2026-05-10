package com.kashta.kala.utils

import java.text.NumberFormat
import java.util.Locale

object FormatHelper {

    private val indianLocale = Locale("en", "IN")

    fun formatNumber(number: Int): String {
        return NumberFormat.getNumberInstance(indianLocale).format(number)
    }

    fun formatCurrency(amount: Double): String {
        val fmt = NumberFormat.getCurrencyInstance(indianLocale)
        fmt.maximumFractionDigits = 0
        return fmt.format(amount)
    }

    fun formatCurrency(amount: Int): String {
        val fmt = NumberFormat.getCurrencyInstance(indianLocale)
        fmt.maximumFractionDigits = 0
        return fmt.format(amount)
    }
}