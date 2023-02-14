package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.ProductDetails
import kotlinx.coroutines.flow.Flow

interface GetBasketListItemUseCase {
    suspend operator fun invoke(productCode: String): Flow<ProductDetails?>
}