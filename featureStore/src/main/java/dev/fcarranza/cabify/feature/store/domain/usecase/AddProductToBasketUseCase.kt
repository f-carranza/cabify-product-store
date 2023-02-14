package dev.fcarranza.cabify.feature.store.domain.usecase

interface AddProductToBasketUseCase {
    suspend operator fun invoke(productId: String, quantity: Int = 1)
}