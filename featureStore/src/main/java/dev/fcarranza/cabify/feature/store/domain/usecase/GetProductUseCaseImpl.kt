package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductUseCase {
    
    override suspend fun invoke(productCode: String): Product? {
        return productRepository.getProduct(productCode)
    }
}