package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.model.Product

interface GetProductWithDiscountsListUseCase {
    suspend operator fun invoke(): List<Product>
}