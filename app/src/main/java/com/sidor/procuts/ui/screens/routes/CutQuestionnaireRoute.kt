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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.cutQuestionnaireScreenInfoLists
import com.sidor.procuts.ui.ToastNotifier
import com.sidor.procuts.ui.screens.CutChoiceScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireLastScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireScreenWithSeveralAnswerOption
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import com.sidor.procuts.ui.viewmodels.AddResult
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CutQuestionnaireRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onAddCutClick: (CutDateInfoDTO) -> Unit,
    clientPhoneNumber: String? = null,
    getClientIdOnPhoneNumber: (String) -> Int?,
    viewModel: CutQuestionnaireViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    clientPhoneNumber?.let { viewModel.setPhoneNumber(it) }
    var isNavigatingForward by rememberSaveable { mutableStateOf(true) }
    var phoneNumberIsExists by rememberSaveable { mutableStateOf(true) }

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
                date = viewModel.getDate()
            )

            CutQuestionnaireScreenType.Choice -> CutChoiceScreen(
                onBack = onPrevQuestion(CutQuestionnaireScreenType.Choice),
                onCutChoice = { cutName: String ->
                    viewModel.setParam("cutName", cutName)
                    onNextQuestion(CutQuestionnaireScreenType.Choice)()
                }
            )

            CutQuestionnaireScreenType.Add -> {
                val ctx = LocalContext.current
                val successMessage = stringResource(R.string.cut_has_been_created)
                CutQuestionnaireLastScreen(
                    onBack = onPrevQuestion(CutQuestionnaireScreenType.Add),
                    onAddCutName = {
                        val addResult = viewModel.tryAddCut(
                            getClientIdOnPhoneNumber = getClientIdOnPhoneNumber,
                            onAddClick = onAddCutClick,
                        )

                        if (addResult == AddResult.SUCCESS) {
                            ToastNotifier(context = ctx).show(message = successMessage)
                            onBack()
                            onNextQuestion(CutQuestionnaireScreenType.Add)()
                        } else if (addResult == AddResult.PHONE_NUMBER_IS_NOT_FOUND) {
                            phoneNumberIsExists = false
                        }
                    },
                    phoneNumber = uiState.clientPhoneNumber,
                    phoneNumberIsExists = phoneNumberIsExists,
                    onSetPhoneNumber = { phoneNumber: String ->
                        viewModel.setPhoneNumber(phoneNumber)
                        phoneNumberIsExists = true
                    },
                )
            }

            else -> {
                val index = screenType.ordinal - 1
                screens[index]()
            }
        }
    }
}