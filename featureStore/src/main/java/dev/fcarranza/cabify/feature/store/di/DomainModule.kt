package dev.fcarranza.cabify.feature.store.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import dev.fcarranza.cabify.feature.store.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetProductWithDiscountsListUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository
    ): GetProductWithDiscountsListUseCase =
        GetProductWithDiscountsListUseCaseImpl(productRepository, discountRepository)

    @Provides
    fun provideGetProductUseCase(productRepository: ProductRepository): GetProductUseCase =
        GetProductUseCaseImpl(productRepository)

    @Provides
    fun provideGetBasketPriceUseCase(): GetBasketTotalUseCase =
        GetBasketTotalUseCaseImpl()

    @Provides
    fun provideGetBasketListUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository,
        basketRepository: BasketRepository,
    ): GetBasketListUseCase =
        GetBasketListUseCaseImpl(
            productRepository = productRepository,
            discountRepository = discountRepository,
            basketRepository = basketRepository
        )

    @Provides
    fun provideGetBasketListItemUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository,
        basketRepository: BasketRepository,
    ): GetBasketListItemUseCase =
        GetBasketListItemUseCaseImpl(
            productRepository = productRepository,
            discountRepository = discountRepository,
            basketRepository = basketRepository
        )

    @Provides
    fun provideAddProductToBasketUseCase(
        basketRepository: BasketRepository,
    ): AddProductToBasketUseCase =
        AddProductToBasketUseCaseImpl(
            basketRepository = basketRepository
        )

    @Provides
    fun provideRemoveProductToBasketUseCase(
        basketRepository: BasketRepository,
    ): RemoveItemBasketUseCase =
        RemoveProductToBasketUseCaseImpl(
            basketRepository = basketRepository
        )

}