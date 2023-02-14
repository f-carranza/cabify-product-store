package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.ProductDetails
import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBasketListItemUseCaseImpl @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val productRepository: ProductRepository,
    private val basketRepository: BasketRepository,
) : GetBasketListItemUseCase {
    override suspend fun invoke(productCode: String): Flow<ProductDetails?> {
        val products = productRepository.getProducts()
        return basketRepository
            .getBasketProducts()
            .map { basketProductCodeList ->
                products.firstOrNull { it.code == productCode }?.let { product ->
                    val quantity = basketProductCodeList.count { it == product.code }
                    val discount = discountRepository.getDiscount(product.code)
                    ProductDetails(
                        product = product,
                        quantity = quantity,
                        discount = discount,
                    )
                }
            }
    }
}