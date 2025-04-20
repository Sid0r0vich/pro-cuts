package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.LocalBoardPadding
import com.sidor.procuts.ui.PaddingSpaces

@Composable
fun PaddingProviderScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    val gridPadding: Dp = 10.dp
    val boardPadding: Dp = 10.dp

    CompositionLocalProvider(LocalBoardPadding provides gridPadding, LocalBoardPadding provides boardPadding) {
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
    paddingSpaces: PaddingSpaces,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingSpaces.toPaddingValues(LocalBoardPadding.current))
    ) {
        Column(
            verticalArrangement = verticalArrangement,
            modifier = Modifier.fillMaxHeight()
        ) {
            content()
        }
    }
}

@Composable
fun PaddingScreenWithBottomButtons(
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    paddingSpaces: PaddingSpaces,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    PaddingScreen(
        modifier = modifier,
        paddingSpaces = paddingSpaces,
        verticalArrangement = verticalArrangement,
    ) {
        Column(
        ) {
            content()
        }
        buttons()
    }
}

@Composable
fun DefaultPaddingScreenWithQuestionnaireButtons(
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    paddingSpaces: PaddingSpaces,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    PaddingScreenWithBottomButtons(
        buttons = {
            Button(
                onClick = onNext,
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.next))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        },
        modifier = modifier,
        paddingSpaces = paddingSpaces,
        content = content,
    )
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
    paddingSpaces: PaddingSpaces = PaddingSpaces(0),
    content: LazyListScope.() -> Unit = {}
) {
    LazyColumn(
        contentPadding = paddingSpaces.toPaddingValues(LocalBoardPadding.current)
    ) {
        content()
    }
}