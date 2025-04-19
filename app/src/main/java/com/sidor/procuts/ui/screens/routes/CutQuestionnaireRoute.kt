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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.data.paramNameList
import com.sidor.procuts.data.paramsList
import com.sidor.procuts.data.questionList
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireLastScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireScreenWithSeveralAnswerOption
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
        val onNextQuestion = {
            isNavigatingForward = true
            viewModel.navigateNext()
        }
        val onBackQuestion = {
            isNavigatingForward = false
            viewModel.navigateBack()
        }


        when (screenType) {
            CutQuestionnaireScreenType.DateName -> CutQuestionnaireFirstScreen(
                onNext = onNextQuestion,
                onBack = onBack,
                onDateChange = { date -> viewModel.setDate(date) },
                onNameChange = { cutName -> viewModel.setParam("cutName", cutName) },
                value = viewModel.getParam("cutName"),
                date = viewModel.getDate()
            )

            CutQuestionnaireScreenType.Add -> CutQuestionnaireLastScreen(
                onBack = {
                    isNavigatingForward = false
                    viewModel.navigateBack()
                },
                onAddCut = {
                    viewModel.addCut()
                    isNavigatingForward = true
                    onBack()
                    viewModel.navigateNext()
                },
            )

            else -> {
                val index = screenType.ordinal - 1
                CutQuestionnaireScreenWithSeveralAnswerOption(
                    onBack = onBackQuestion,
                    onNext = onNextQuestion,
                    text = stringResource(questionList[index]),
                    name = stringResource(paramNameList[index]),
                    defaultValue = viewModel.getParam(CutQuestionnaireViewModel.paramNames[index]),
                    onValueChange = { value ->
                        viewModel.setParam(CutQuestionnaireViewModel.paramNames[index], value)
                    },
                    valuesList = paramsList[index].map { stringResource(it) },
                )
            }
        }
    }
}