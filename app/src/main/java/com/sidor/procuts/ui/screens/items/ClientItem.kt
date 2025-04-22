package com.sidor.procuts.ui.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun ClientItem(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    loadingIsCompleted: Boolean = true
) {
    DefaultItem(
        modifier = modifier,
        text = name,
        onClick = onClick,
        loadingIsCompleted = loadingIsCompleted
    )
}