package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.CutCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutScreen(
    cutName: String,
    imgId: Int,
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
        PaddingScreen(
            horizontalSpaceCount = 4,
            verticalSpaceCount = 2,
        ) {
            CutCard(cutName, imgId)
            DefaultSpacer(1)

        }
    }
}