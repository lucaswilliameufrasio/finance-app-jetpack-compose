package com.example.financeapp

import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("MMM dd, hh:mm a")
    return dateFormat.format(this)
}

fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this)
}