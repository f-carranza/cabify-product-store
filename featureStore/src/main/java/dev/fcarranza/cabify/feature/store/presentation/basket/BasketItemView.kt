package dev.fcarranza.cabify.feature.store.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.fcarranza.cabify.feature.store.domain.dto.BasketItem
import dev.fcarranza.cabify.feature.store.presentation.utils.CurrencyAux

@Composable
fun BasketItemView(
    modifier: Modifier = Modifier, item: BasketItem
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(40.dp),
                model = item.product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = item.product.name, style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colors.primaryVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "x${item.quantity}",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(
                modifier = Modifier.weight(1f, true)
            )

            Text(text = with(object : CurrencyAux {}) {
                item.finalPrice.getCurrencyFormat(item.product.currencyCode)
            }, style = MaterialTheme.typography.body1
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.End
        ) {

            item.withoutDiscountPrice?.let {
                Text(
                    text = item.discount!!.getTitle(LocalContext.current),
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = with(object : CurrencyAux {}) {
                        it.getCurrencyFormat(item.product.currencyCode)
                    },
                    style = MaterialTheme.typography.caption,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
