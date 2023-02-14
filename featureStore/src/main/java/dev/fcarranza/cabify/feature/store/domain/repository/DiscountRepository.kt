package dev.fcarranza.cabify.feature.store.domain.repository

import dev.fcarranza.cabify.feature.store.domain.model.Discount

interface DiscountRepository {
    suspend fun getDiscount(productCode: String): Discount?
}