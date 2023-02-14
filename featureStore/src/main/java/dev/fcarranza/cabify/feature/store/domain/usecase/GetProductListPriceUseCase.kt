package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.model.Product

interface GetProductListPriceUseCase {
    operator fun invoke(products: List<Product>): Double
}