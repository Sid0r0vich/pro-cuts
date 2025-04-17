package com.sidor.procuts.ui.screens.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClientItem(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    DefaultItem(
        modifier = modifier,
        text = name,
        onClick = onClick
    )
}