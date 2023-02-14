package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import javax.inject.Inject

class RemoveProductToBasketUseCaseImpl @Inject constructor(
    val basketRepository: BasketRepository
) : RemoveItemBasketUseCase {
    override suspend fun invoke(productCode: String) {
        basketRepository.removeProduct(productCode)
    }
}