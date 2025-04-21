package com.sidor.procuts.ui.screens.topbars

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.theme.LocalColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalColorPalette.current.barColor,
        ),
        actions = actions
    )
}

@Composable
fun TitleTopAppBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBack: () -> Unit
) {
    DefaultTopAppBar (
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = LocalColorPalette.current.mainColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.button_back),
                    tint = LocalColorPalette.current.mainColor
                )
            }
        },
        actions = actions
    )
}