package com.sidor.procuts.ui.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    navigationIconContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = navigationIconContent,
        modifier = modifier,
        colors=TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}