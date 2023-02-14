package dev.fcarranza.cabify.feature.store.data.repository

import dev.fcarranza.cabify.feature.store.data.datasource.MockDiscountDatasource
import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import javax.inject.Inject

class DiscountRepositoryImpl @Inject constructor(
    private val discountDatasource: MockDiscountDatasource
) : DiscountRepository {

    override suspend fun getDiscount(productCode: String): Discount? {
        return discountDatasource.getDiscount(productCode)
    }
}