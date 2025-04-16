package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientAppBar(
    title: String,
    navigationIconContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    MyTopAppBar (
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        navigationIconContent = navigationIconContent,
        modifier = modifier,
    )
}