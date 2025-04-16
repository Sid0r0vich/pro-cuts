package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sidor.procuts.ui.LocalGridPadding

@Composable
fun DefaultSpacer(spaceCount: Int = 1) {
    Spacer(modifier = Modifier.size((spaceCount * LocalGridPadding.current.value).dp))
}