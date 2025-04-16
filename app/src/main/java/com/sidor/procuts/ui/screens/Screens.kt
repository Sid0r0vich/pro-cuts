package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sidor.procuts.ui.LocalBoardPadding
import com.sidor.procuts.ui.LocalGridPadding

@Composable
fun PaddingProviderScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    val gridPadding: Dp = 10.dp
    val boardPadding: Dp = 20.dp

    CompositionLocalProvider(LocalGridPadding provides gridPadding, LocalBoardPadding provides boardPadding) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun PaddingScreen(
    modifier: Modifier = Modifier,

    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = LocalGridPadding.current * 2)
    ) {
        content()
    }
}

@Composable
fun TopAppBarScreen(
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    PaddingProviderScreen(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topBar()
            content()
        }
    }
}

@Composable
fun LazyScreen(
    content: LazyListScope.() -> Unit
) {
    LazyColumn { content() }
}

@Composable
fun LazyPaddingScreen(
    verticalSpaceCount: Int = 2,
    horizontalSpaceCount: Int = 2,
    content: LazyListScope.() -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = LocalGridPadding.current * horizontalSpaceCount)
    ) {
        item {
            DefaultSpacer(verticalSpaceCount)
        }
        content()
        item {
            DefaultSpacer(verticalSpaceCount)
        }
    }
}