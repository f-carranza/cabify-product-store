package dev.fcarranza.cabify.feature.store.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fcarranza.cabify.baselib.presentation.base.BaseViewModel
import dev.fcarranza.cabify.baselib.presentation.base.UiEvent
import dev.fcarranza.cabify.baselib.presentation.base.UiState
import dev.fcarranza.cabify.feature.store.domain.dto.ProductDetails
import dev.fcarranza.cabify.feature.store.domain.usecase.AddProductToBasketUseCase
import dev.fcarranza.cabify.feature.store.domain.usecase.GetBasketListItemUseCase
import dev.fcarranza.cabify.feature.store.domain.usecase.RemoveItemBasketUseCase
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val getBasketListItemUseCase: GetBasketListItemUseCase,
    val addProductToBasketUseCase: AddProductToBasketUseCase,
    val removeProductToBasketUseCase: RemoveItemBasketUseCase,
) : BaseViewModel<
        ProductDetailViewModel.State,
        ProductDetailViewModel.Event,
        >() {

    override fun createInitialState(): State = State()

    private val productId: String
        get() = savedStateHandle.get<String>(GraphNavigation.KEY_PRODUCT_ID) ?: throw IllegalStateException("Product id not found")

    override suspend fun init() {
        super.init()
        getBasketListItemUseCase(productId).collect {
            setState { copy(productDetails = it) }
        }
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.AddProduct -> addProductToBasketUseCase(productId)
            is Event.RemoveProduct -> removeProductToBasketUseCase(productId)
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val productDetails: ProductDetails? = null,
    ) : UiState

    sealed class Event : UiEvent {
        object AddProduct : Event()
        object RemoveProduct : Event()
    }
}