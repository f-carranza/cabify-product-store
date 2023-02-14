package dev.fcarranza.cabify.feature.store.presentation.utils

import java.text.NumberFormat
import java.util.*

interface CurrencyAux {
    fun Double.getCurrencyFormat(currencyCode: String = "EUR"): String =
        NumberFormat.getCurrencyInstance().also {
            it.currency = Currency.getInstance(currencyCode)
        }.format(this)
}