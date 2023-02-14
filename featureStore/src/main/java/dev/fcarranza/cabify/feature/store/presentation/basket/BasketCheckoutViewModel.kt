package dev.fcarranza.cabify.feature.store.presentation.basket

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fcarranza.cabify.baselib.presentation.base.BaseViewModel
import dev.fcarranza.cabify.baselib.presentation.base.UiEvent
import dev.fcarranza.cabify.baselib.presentation.base.UiState
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationCommand
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationManager
import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.domain.usecase.GetBasketListUseCase
import dev.fcarranza.cabify.feature.store.domain.usecase.GetBasketTotalUseCase
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation
import javax.inject.Inject

@HiltViewModel
class BasketCheckoutViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getBasketListUseCase: GetBasketListUseCase,
    private val getBasketPriceUseCase: GetBasketTotalUseCase
) : BaseViewModel<BasketCheckoutViewModel.State, BasketCheckoutViewModel.Event>() {

    override fun createInitialState(): State = State()

    override suspend fun init() {
        super.init()
        getBasketListUseCase().collect {
            setState {
                copy(
                    basketItems = it,
                    price = getBasketPriceUseCase(it)
                )
            }
        }
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.OnItemClicked -> navigationManager.navigate(
                GraphNavigation.productDetailDialog(event.product.code)
            )
            is Event.Back ->{navigationManager.navigate(NavigationCommand.GoBack())}
        }
    }

    data class State(
        val basketItems: List<BasketItem> = emptyList(),
        val price: Double = 0.0
    ) : UiState

    sealed class Event : UiEvent {
        data class OnItemClicked(val product: Product) : Event()
        data class Back(val param:Unit? = null) : Event()
    }

}