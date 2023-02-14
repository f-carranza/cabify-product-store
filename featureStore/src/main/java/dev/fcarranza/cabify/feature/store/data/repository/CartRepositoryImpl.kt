package dev.fcarranza.cabify.feature.store.data.repository

import dev.fcarranza.cabify.feature.store.data.datasource.DBBasketDatasource
import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDatasource: DBBasketDatasource
) : BasketRepository {

    override suspend fun getBasketProducts(): Flow<List<String>> {
        return basketDatasource.getBasket()
    }

    override suspend fun addProduct(productCode: String) {
        basketDatasource.addProduct(productCode)
    }

    override suspend fun addProducts(productCode: String, quantity: Int) {
        basketDatasource.addProducts(productCode, quantity)
    }

    override suspend fun removeProduct(productCode: String) {
        basketDatasource.removeProduct(productCode)
    }

}