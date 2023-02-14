package dev.fcarranza.cabify.feature.store.presentation.basket

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fcarranza.cabify.baselib.presentation.base.ViewModelScreen
import dev.fcarranza.cabify.baselib.presentation.base.ds.TopBar
import dev.fcarranza.cabify.feature.store.R
import dev.fcarranza.cabify.feature.store.presentation.utils.CurrencyAux
import dev.fcarranza.cabify.feature.store.presentation.view.BasketItemView
import dev.fcarranza.cabify.feature.store.presentation.view.TotalPriceView

@Composable
fun BasketCheckoutView(
    viewModel: BasketCheckoutViewModel
) {
    ViewModelScreen(
        viewModel = viewModel
    ) { state ->
        BasketCheckoutView(state) { event ->
            viewModel.setEvent(event)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BasketCheckoutView(
    state: BasketCheckoutViewModel.State, event: (BasketCheckoutViewModel.Event) -> Unit
) {
    Scaffold(Modifier.background(MaterialTheme.colors.surface), topBar = {
        TopBar(title = stringResource(id = R.string.basket_title),
            back = { event(BasketCheckoutViewModel.Event.Back()) })
    }) {
        Column(
            Modifier.verticalScroll(rememberScrollState()).background(MaterialTheme.colors.surface)
        ) {

            Column(
                Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface),

                ) {
                state.basketItems.forEach { item ->
                    BasketItemView(
                        modifier = Modifier.clickable {
                            event(BasketCheckoutViewModel.Event.OnItemClicked(product = item.product))
                        }, item = item
                    )
                }
            }
            Divider(Modifier.padding(8.dp))

            TotalPriceView(modifier = Modifier
                .defaultMinSize(minHeight = 64.dp)
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = 16.dp),
                price = with(object : CurrencyAux {}) {
                    state.price.getCurrencyFormat()
                })
        }
    }
}
