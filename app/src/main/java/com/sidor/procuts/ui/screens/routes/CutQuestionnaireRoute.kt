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
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.cutQuestionnaireScreenInfoLists
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireLastScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireScreenWithSeveralAnswerOption
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CutQuestionnaireRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onAddCutClick: (CutDateInfoDTO) -> Unit,
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
        val onNextQuestion = { cutQuestionnaireScreenType: CutQuestionnaireScreenType ->
            {
                isNavigatingForward = true
                viewModel.navigate(
                    viewModel.getNextScreen(
                        CutQuestionnaireScreenType.entries[cutQuestionnaireScreenType.ordinal]
                    )
                )
            }
        }
        val onPrevQuestion = { cutQuestionnaireScreenType: CutQuestionnaireScreenType ->
            {
                isNavigatingForward = false
                viewModel.navigate(
                    viewModel.getPrevScreen(
                        CutQuestionnaireScreenType.entries[cutQuestionnaireScreenType.ordinal]
                    )
                )
            }
        }

        val screens = cutQuestionnaireScreenInfoLists
            .map { screen ->
                @Composable {
                    CutQuestionnaireScreenWithSeveralAnswerOption(
                        onBack = onPrevQuestion(screen.screenType),
                        onNext = onNextQuestion(screen.screenType),
                        text = stringResource(screen.questionId),
                        name = stringResource(screen.paramLabelId),
                        defaultValue = viewModel.getParam(screen.paramName),
                        onValueChange = { value ->
                            viewModel.setParam(screen.paramName, value)
                        },
                        valuesList = screen.paramIdList.map { stringResource(it) },
                    )
                }
            }

        when (screenType) {
            CutQuestionnaireScreenType.DateName -> CutQuestionnaireFirstScreen(
                onNext = onNextQuestion(CutQuestionnaireScreenType.DateName),
                onBack = onBack,
                onDateChange = { date -> viewModel.setDate(date) },
                onNameChange = { cutName -> viewModel.setParam("cutName", cutName) },
                value = viewModel.getParam("cutName"),
                date = viewModel.getDate()
            )

            CutQuestionnaireScreenType.Add -> CutQuestionnaireLastScreen(
                onBack = onPrevQuestion(CutQuestionnaireScreenType.Add),
                onAddCut = {
                    viewModel.addCut(onAddCutClick)
                    onBack()
                    onNextQuestion(CutQuestionnaireScreenType.Add)()
                },
                cutParams = uiState.paramsMap
            )

            else -> {
                val index = screenType.ordinal - 1
                screens[index]()
            }
        }
    }
}