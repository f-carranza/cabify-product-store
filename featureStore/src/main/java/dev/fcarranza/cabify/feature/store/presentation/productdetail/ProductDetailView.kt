package dev.fcarranza.cabify.feature.store.presentation.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.fcarranza.cabify.baselib.presentation.base.ViewModelScreen
import dev.fcarranza.cabify.baselib.presentation.base.ds.AdderRemover
import dev.fcarranza.cabify.feature.store.domain.dto.ProductDetails
import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.presentation.utils.CurrencyAux
import dev.fcarranza.cabify.feature.store.presentation.view.DiscountView

@Composable
fun ProductDialogView(
    viewModel: ProductDetailViewModel,
) {
    ViewModelScreen(viewModel = viewModel, content = { state ->
        ProductDialogView(state) { event ->
            viewModel.setEvent(event)
        }
    })
}

@Composable
private fun ProductDialogView(
    state: ProductDetailViewModel.State, events: (event: ProductDetailViewModel.Event) -> Unit
) {

    Surface(
        Modifier.wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = state.productDetails?.product?.name ?: "",
                style = MaterialTheme.typography.h5
            )
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .requiredHeightIn(150.dp, 150.dp)
                        .align(Center),
                    model = state.productDetails?.product?.imageUrl,
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }
            PriceContainerView(product = state.productDetails?.product)

            state.productDetails?.discount?.let {
                Spacer(modifier = Modifier.height(8.dp))
                DiscountView(discount = it)
            }
            Divider(Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.ShoppingBasket,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier,
                    text = state.productDetails?.quantity?.toString() ?: "-",
                    style = MaterialTheme.typography.h6
                )
                Spacer(
                    modifier = Modifier.weight(1f, true)
                )
                AdderRemover(
                    modifier = Modifier.defaultMinSize(minHeight = 80.dp),
                    addIcon = Icons.Outlined.Add,
                    addText = "Add",
                    subIcon = Icons.Outlined.Remove,
                    onAddClick = {
                        events(ProductDetailViewModel.Event.AddProduct)
                    },
                    onSubClick = if (state.productDetails?.quantity?.let {  it >0} == true) {
                        {
                            events(ProductDetailViewModel.Event.RemoveProduct)
                        }
                    } else {
                        null
                    },
                )
            }
        }
    }
}

@Composable
fun PriceContainerView(
    modifier: Modifier = Modifier,
    product: Product?,
) {
    Text(modifier = modifier
        .clip(MaterialTheme.shapes.large)
        .background(MaterialTheme.colors.surface)
        .padding(vertical = 8.dp, horizontal = 12.dp),
        text = with(object : CurrencyAux {}) {
            product?.price?.getCurrencyFormat(
                product.currencyCode
            ) ?: ""
        },
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h4
    )
}

@Preview(showBackground = false)
@Composable
fun ProductDialogViewRender() {
    ProductDialogView(
        state = ProductDetailViewModel.State(
            productDetails = ProductDetails(
                product = Product(
                    code = "TSHIRT",
                    name = "Cabify T-Shirt",
                    imageUrl = "https://i.ibb.co/8NqzY73/tshirt.jpg",
                    price = 10.0,
                    currencyCode = "EUR",

                    ), discount = Discount.Quantity(3, 1), quantity = 6
            )
        )
    ) {

    }
}