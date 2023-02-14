package dev.fcarranza.cabify.feature.store.data.repository

import dev.fcarranza.cabify.feature.store.data.datasource.NetworkProductDatasource
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: NetworkProductDatasource,
) : ProductRepository {

    private var products: List<Product> = emptyList()

    override suspend fun getProducts(): List<Product> {

        return products.takeIf { it.isNotEmpty() } ?: productDataSource.getAllProducts().also {
            products = it
        }
    }

    override suspend fun getProduct(id: String): Product? {
        return getProducts().firstOrNull { it.code == id }
    }
}