package dev.fcarranza.cabify.feature.store.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fcarranza.cabify.baselib.presentation.base.BaseViewModel
import dev.fcarranza.cabify.baselib.presentation.base.UiEvent
import dev.fcarranza.cabify.baselib.presentation.base.UiState
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationManager
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.usecase.GetBasketListUseCase
import dev.fcarranza.cabify.feature.store.domain.usecase.GetProductWithDiscountsListUseCase
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getProductWithDiscountsListUseCase: GetProductWithDiscountsListUseCase,
    private val getBasketListUseCase: GetBasketListUseCase
    ) : BaseViewModel<StoreViewModel.State, StoreViewModel.Event>() {

    override fun createInitialState(): State = State(
        isLoading = true
    )

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ProductClicked -> {
                navigationManager.navigate(
                    GraphNavigation.productDetailDialog(productId = event.product.code)
                )
            }
            Event.OnGoToCheckout -> navigationManager.navigate(
                GraphNavigation.checkout
            )
        }
    }

    override suspend fun init() {
        super.init()
        val productsWithDiscounts = getProductWithDiscountsListUseCase()
        getBasketListUseCase().collect {
            setState {
                copy(
                    isLoading = false,
                    products = productsWithDiscounts,
                    basketItems = it.sumOf { it.quantity }
                )
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val basketItems : Int = 0
    ) : UiState

    sealed class Event : UiEvent {
        data class ProductClicked(val product: Product) : Event()
        object OnGoToCheckout : Event()
    }
}