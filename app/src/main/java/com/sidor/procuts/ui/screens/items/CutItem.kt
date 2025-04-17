package com.sidor.procuts.ui.screens.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R

@Composable
fun CutItem(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    DefaultItem(
        modifier = modifier,
        text = stringResource(R.string.cut_card),
        onClick = onClick
    )
}