package com.sidor.procuts.ui.screens.routes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireSecondScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireThirdScreen
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireScreenType
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CutQuestionnaireRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: CutQuestionnaireViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    var isNavigatingForward by remember { mutableStateOf(true) }

    AnimatedContent(
        targetState = uiState.screenType,
        transitionSpec = {
            if (isNavigatingForward) {
                slideInHorizontally(initialOffsetX = { it }) with slideOutHorizontally(targetOffsetX = { -it })
            } else {
                slideInHorizontally(initialOffsetX = { -it }) with slideOutHorizontally(targetOffsetX = { it })
            }
        }
    ) { screenType ->
        when (screenType) {
            CutQuestionnaireScreenType.DateName -> CutQuestionnaireFirstScreen(
                onNext = {
                    isNavigatingForward = true
                    viewModel.navigateParameters()
                         },
                onBack = {
                    isNavigatingForward = false
                    onBack()
                },
                onDateChange = { date -> viewModel.setDate(date) },
                onNameChange = { cutName -> viewModel.setCutName(cutName) }
            )

            CutQuestionnaireScreenType.Parameters -> CutQuestionnaireSecondScreen(
                onNext = {
                    isNavigatingForward = true
                    viewModel.navigateAdd()
                },
                onBack = {
                    isNavigatingForward = false
                    viewModel.navigateDateName()
                },
                onCutFrequencyChange = { cutFrequency -> viewModel.setCutFrequency(cutFrequency) }
            )

            CutQuestionnaireScreenType.Add -> CutQuestionnaireThirdScreen(
                onBack = {
                    isNavigatingForward = false
                    viewModel.navigateParameters()
                },
                onAddCut = {
                    viewModel.addCut()
                    isNavigatingForward = true
                    onBack()
                    viewModel.navigateDateName()
                },
            )
        }
    }
}