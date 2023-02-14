package dev.fcarranza.cabify.feature.store.domain.model

import android.content.Context
import dev.fcarranza.cabify.feature.store.R

sealed class Discount {
    data class PercentByNumber(val productsNumber: Int, val discountPercent: Float) : Discount()

    data class Quantity(val productsNumber: Int, val giftNumber: Int) : Discount()

    fun getTitle(context: Context): String {
        return when (this) {
            is PercentByNumber -> context.getString(
                R.string.discount_title_discount_by_amount,
                this.productsNumber,
                (this.discountPercent * 100).toInt()
            )
            is Quantity -> context.getString(
                R.string.discount_title_take_x_get_y,
                this.productsNumber,
                this.giftNumber
            )
        }
    }
}