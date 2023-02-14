package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.model.Product

interface GetProductUseCase {

    suspend operator fun invoke(productCode: String): Product?
}