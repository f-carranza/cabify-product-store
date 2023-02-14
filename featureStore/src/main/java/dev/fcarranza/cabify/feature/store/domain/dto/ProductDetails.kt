package dev.fcarranza.cabify.feature.store.domain.dto

import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.model.Product

data class ProductDetails(
    val product: Product,
    val quantity: Int = 0,
    val discount: Discount?,
)