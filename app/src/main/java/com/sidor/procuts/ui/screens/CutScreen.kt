package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.Cut
import com.sidor.procuts.ui.screens.cards.CutCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutScreen(
    cut: Cut,
    onBack: () -> Unit,
) {
    TopAppBarScreen(
            topBar = {
                TitleTopAppBar(
                    title = stringResource(R.string.cut_tab_app_bar),
                    onBack = onBack
                )
            },
    ) {
        LazyPaddingScreen(
            horizontalSpaceCount = 2,
            verticalSpaceCount = 2,
        ) {
            item {
                CutCard(cut)
            }
        }
    }
}