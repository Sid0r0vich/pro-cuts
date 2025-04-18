package com.sidor.procuts.ui.screens


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCutQuestionnaireScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit) = {}
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        DefaultPaddingScreenWithQuestionnaireButtons(
            onNext = onNext,
            onBack = onBack,
            horizontalSpaceCount = 2,
            verticalSpaceCount = 2,
        ) {
                content()
        }
    }
}

