package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem
import kotlinx.coroutines.flow.Flow

interface GetBasketListUseCase {
    suspend operator fun invoke(): Flow<List<BasketItem>>
}