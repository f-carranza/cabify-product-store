package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository

class GetProductWithDiscountsListUseCaseImpl(
    private val productRepository: ProductRepository,
    private val discountRepository: DiscountRepository
) : GetProductWithDiscountsListUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts().map {
            it.discount = discountRepository.getDiscount(it.code)
            it
        }
    }
}