package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import javax.inject.Inject

class AddProductToBasketUseCaseImpl @Inject constructor(
    val basketRepository: BasketRepository
) : AddProductToBasketUseCase {
    override suspend fun invoke(productId: String, quantity: Int) {
        basketRepository.addProducts(productId, quantity)
    }
}