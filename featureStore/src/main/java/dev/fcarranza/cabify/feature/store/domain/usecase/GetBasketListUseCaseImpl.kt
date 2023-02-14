package dev.fcarranza.cabify.feature.store.domain.usecase

import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem
import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBasketListUseCaseImpl @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val productRepository: ProductRepository,
    private val basketRepository: BasketRepository,
) : GetBasketListUseCase {
    override suspend fun invoke(): Flow<List<BasketItem>> {
        val products = productRepository.getProducts()
        return basketRepository.getBasketProducts().map { productCodeList ->
            products.filter { productCodeList.contains(it.code) }
                .map { product ->
                    val quantity = productCodeList.count { it == product.code }
                    val discount = discountRepository.getDiscount(product.code)
                    val prices =
                        getPrices(product = product, quantity = quantity, discount = discount)
                    BasketItem(
                        product = product,
                        quantity = quantity,
                        discount = discount,
                        finalPrice = prices.finalPrice,
                        withoutDiscountPrice = prices.withoutDiscountPriceIfDifferent
                    )
                }
        }
    }

    private fun getPrices(
        product: Product,
        quantity: Int,
        discount: Discount?
    ): BasketItemPrice {
        val withoutDiscountPrice = product.price * quantity
        val totalPrice = discount?.let {
            getPriceWithDiscount(product, quantity, it)
        } ?: withoutDiscountPrice
        return BasketItemPrice(totalPrice, withoutDiscountPrice)

    }

    private fun getPriceWithDiscount(
        product: Product,
        quantity: Int,
        discount: Discount
    ): Double {
        return when (discount) {
            is Discount.PercentByNumber -> {
                if (quantity >= discount.productsNumber) {
                    product.price * quantity * (1 - discount.discountPercent)
                } else {
                    product.price * quantity
                }
            }
            is Discount.Quantity -> {
                val blockSize: Int = (discount.productsNumber + discount.giftNumber)
                val numberOfBlocks: Int = quantity / blockSize
                val rest = quantity % blockSize
                return numberOfBlocks * product.price * discount.productsNumber + rest * product.price
            }
        }
    }

    private data class BasketItemPrice(
        val finalPrice: Double,
        val withoutDiscountPrice: Double
    ) {
        val withoutDiscountPriceIfDifferent: Double?
            get() = if (finalPrice != withoutDiscountPrice) withoutDiscountPrice else null
    }
}