package com.fdlr.testemb.coreds.utils

import java.text.NumberFormat
import java.util.Locale

fun Double?.toBrazilianCurrency(): String {
    if (this == null) return "R$ 0,00"
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}

fun Double?.toDollarCurrency(): String {
    if (this == null) return "$ 0.00"
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}