package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.PaddingScreen
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.state.ErrorScreen
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireErrorScreen(
    onBack: () -> Unit,
    title: String,
    errorMessage: String,
    onRetry: () -> Unit
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = title,
                onBack = onBack
            )
        },
    ) {
        PaddingScreen(
            paddingSpaces = PaddingSpaces(horizontal = 2, top = 2, bottom = 1),
        ) {
            ErrorScreen(
                errorMessage = errorMessage,
                onRetry = onRetry
            )
        }
    }
}