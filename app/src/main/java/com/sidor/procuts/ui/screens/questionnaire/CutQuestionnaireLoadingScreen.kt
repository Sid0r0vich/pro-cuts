package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.PaddingScreen
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.state.LoadingScreen
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireLoadingScreen(
    onBack: () -> Unit,
    title: String
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
            LoadingScreen()
        }
    }
}