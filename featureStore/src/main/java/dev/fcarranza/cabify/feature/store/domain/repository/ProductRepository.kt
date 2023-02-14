package dev.fcarranza.cabify.feature.store.domain.repository

import dev.fcarranza.cabify.feature.store.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id: String): Product?

}