package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem

interface GetBasketTotalUseCase {
    operator fun invoke(basketItems: List<BasketItem>): Double
}