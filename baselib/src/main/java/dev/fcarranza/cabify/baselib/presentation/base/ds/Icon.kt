package dev.fcarranza.cabify.baselib.presentation.base.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small
            )
            .padding( 4.dp)
    ) {
        if (icon != null) {
            Icon(
                modifier = modifier,
                imageVector = icon,
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
@Preview
fun IconPreview() {
    Icon(
        icon = Icons.Default.Redeem,
    )
}