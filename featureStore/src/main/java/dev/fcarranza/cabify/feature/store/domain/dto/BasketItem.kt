package dev.fcarranza.cabify.feature.store.domain.dto

import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.model.Product

data class BasketItem(
    val product: Product,
    val quantity: Int,
    val discount: Discount?,
    val finalPrice: Double,
    val withoutDiscountPrice: Double?
)