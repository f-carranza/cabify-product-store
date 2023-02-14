package dev.fcarranza.cabify.feature.store.data.datasource

import dev.fcarranza.cabify.feature.store.data.mapper.ProductMapper
import dev.fcarranza.cabify.feature.store.data.service.ProductAPIService
import dev.fcarranza.cabify.feature.store.domain.model.Product
import javax.inject.Inject

class NetworkProductDatasource @Inject constructor(
    private val productAPIService: ProductAPIService,
) : ProductMapper {

    suspend fun getAllProducts(): List<Product> =
        productAPIService.getProducts().products.map { it.toProduct() }
}