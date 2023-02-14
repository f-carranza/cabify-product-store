package dev.fcarranza.cabify.feature.store.domain.repository

import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    suspend fun getBasketProducts(): Flow<List<String>>

    suspend fun addProduct(productCode: String)

    suspend fun addProducts(productCode: String, quantity: Int)

    suspend fun removeProduct(productCode: String)

}