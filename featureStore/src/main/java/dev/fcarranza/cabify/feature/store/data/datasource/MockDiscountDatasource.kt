package dev.fcarranza.cabify.feature.store.data.datasource

import dev.fcarranza.cabify.feature.store.domain.model.Discount


class MockDiscountDatasource {
    fun getDiscount(productCode: String): Discount? {
        return when (productCode) {
            "VOUCHER" -> Discount.Quantity(1, 1)
            "TSHIRT" -> Discount.PercentByNumber(3, 0.05f)
            else -> null
        }
    }
}