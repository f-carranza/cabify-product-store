package dev.fcarranza.cabify.feature.store.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fcarranza.cabify.baselib.presentation.base.ViewModelScreen
import dev.fcarranza.cabify.baselib.presentation.base.ds.TopBar
import dev.fcarranza.cabify.feature.store.R.string.*


@Composable
fun StoreView(
    viewModel: StoreViewModel
) {
    ViewModelScreen(
        viewModel = viewModel
    ) { state ->
        StoreView(state) { event ->
            viewModel.setEvent(event)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun StoreView(
    state: StoreViewModel.State, events: (event: StoreViewModel.Event) -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = store_title), actions = {
                IconButton(onClick = {
                    events(StoreViewModel.Event.OnGoToCheckout)
                }) {
                    BadgedBox(
                        badge = {
                            if (state.basketItems > 0) {
                                Badge (backgroundColor = MaterialTheme.colors.primary){
                                    Text(state.basketItems.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.ShoppingBasket, contentDescription = null
                        )
                    }
                }
            })
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.products) { product ->
                StoreRow(modifier = Modifier,
                    product = product,
                    discount = product.discount,
                    onClick = {
                        events(StoreViewModel.Event.ProductClicked(product))
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreViewPreview() {
    StoreView(
        state = StoreViewModel.State()
    ) {

    }
}