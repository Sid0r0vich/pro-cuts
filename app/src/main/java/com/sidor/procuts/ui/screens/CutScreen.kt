package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.CutCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutScreen(
    cut: CutDTO,
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
            paddingSpaces = PaddingSpaces(2)
        ) {
            item {
                CutCard(cut)
            }
        }
    }
}