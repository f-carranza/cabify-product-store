package dev.fcarranza.cabify.feature.store.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.fcarranza.cabify.baselib.presentation.base.ds.Icon
import dev.fcarranza.cabify.feature.store.domain.model.Discount
import dev.fcarranza.cabify.feature.store.domain.model.Product
import dev.fcarranza.cabify.feature.store.presentation.utils.CurrencyAux

@Composable
fun StoreRow(
    modifier: Modifier = Modifier,
    product: Product,
    discount: Discount? = null,
    onClick: (() -> Unit)?
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    enabled = onClick != null,
                    onClick = { onClick?.invoke() }
                )
                .padding(16.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = product.imageUrl,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = with(object : CurrencyAux {}) {
                            product.price.getCurrencyFormat(product.currencyCode)
                        },
                        style = MaterialTheme.typography.h6
                    )
                    if (discount != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(24.dp),
                            icon = Icons.Default.Redeem
                        )
                    }
                }
            }
        }
    }
}
