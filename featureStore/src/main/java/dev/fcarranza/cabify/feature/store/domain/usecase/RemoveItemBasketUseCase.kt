package dev.fcarranza.cabify.feature.store.domain.usecase

interface RemoveItemBasketUseCase {
    suspend operator fun invoke(productCode: String)
}