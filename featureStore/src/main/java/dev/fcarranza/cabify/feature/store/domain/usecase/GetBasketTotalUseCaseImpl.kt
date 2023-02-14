package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem

class GetBasketTotalUseCaseImpl : GetBasketTotalUseCase {
    override fun invoke(basketItems: List<BasketItem>): Double {
        return basketItems.sumOf { it.finalPrice }
    }
}