package dev.fcarranza.cabify.baselib.presentation.base.ds

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    back: (()->Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    TopAppBar(
        title = { TopBarTitle(title) },
        modifier = modifier,
        navigationIcon = {
            back?.let {
                IconButton(onClick = { it.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            }
        },
        actions = actions ?: {},
        contentColor = MaterialTheme.colors.primary,
        backgroundColor = MaterialTheme.colors.surface,
    )
}

@Composable
fun TopBarTitle(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        text = title,
    )
}