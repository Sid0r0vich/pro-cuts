package com.sidor.procuts.ui.screens.routes

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.CameraActivity
import com.sidor.procuts.R
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.ui.components.ToastNotifier
import com.sidor.procuts.ui.screens.questionnaire.CameraClaimScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireClientChoiceScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireConfirmScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireCutChoiceScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireDateScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireErrorScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireLoadingScreen
import com.sidor.procuts.ui.screens.questionnaire.CutQuestionnaireScreenWithSeveralAnswerOption
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import com.sidor.procuts.ui.viewmodels.AddResult
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireViewModel
import com.sidor.procuts.ui.viewmodels.QuestionnaireUIState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CutQuestionnaireRoute(
    onBack: () -> Unit,
    onAddCutClick: (CutDateInfoDTO) -> Unit,
    clientDTO: ClientDTO?,
    viewModel: CutQuestionnaireViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    var isNavigatingForward by rememberSaveable { mutableStateOf(true) }
    val clientRecentCutIds =
        viewModel.getClientCutDates(clientDTO?.id)
            .map { cutDateStateFlow -> cutDateStateFlow.collectAsState().value }
            .map { cutDateDTO: CutDateDTO -> cutDateDTO.cutId }
            .distinct()


    AnimatedContent(
        targetState = Pair(uiState.screenType, uiState.questionInd),
        transitionSpec = {
            if (isNavigatingForward) {
                slideInHorizontally(initialOffsetX = { it }).togetherWith(slideOutHorizontally(targetOffsetX = { -it }))
            } else {
                slideInHorizontally(initialOffsetX = { -it }).togetherWith(slideOutHorizontally(targetOffsetX = { it }))
            }
        }
    ) { (screenType, questionInd) ->
        val onNextScreenType = { cutQuestionnaireScreenType: CutQuestionnaireScreenType ->
            {
                isNavigatingForward = true
                viewModel.navigate(
                    viewModel.getNextScreen(
                        CutQuestionnaireScreenType.entries[cutQuestionnaireScreenType.ordinal]
                    )
                )
            }
        }
        val onPrevScreenType = { cutQuestionnaireScreenType: CutQuestionnaireScreenType ->
            {
                isNavigatingForward = false
                viewModel.navigate(
                    viewModel.getPrevScreen(
                        CutQuestionnaireScreenType.entries[cutQuestionnaireScreenType.ordinal]
                    )
                )
            }
        }

        val questionList = when(val state = viewModel.questionnaireUIState) {
            is QuestionnaireUIState.Success -> state.questions
            else -> null
        }
        val screens = questionList
                ?.withIndex()
                ?.map { (ind, question) ->
                    @Composable {
                        val defaultValue = uiState.paramsMap[question.question]
                            ?: if (question.options.isNotEmpty()) question.options[0] else ""
                        CutQuestionnaireScreenWithSeveralAnswerOption(
                            onBack = {
                                isNavigatingForward = false
                                if (ind == 0) onPrevScreenType(CutQuestionnaireScreenType.Question)()
                                else viewModel.setQuestionInd(ind - 1)
                            },
                            onNext = {
                                isNavigatingForward = true
                                if (ind == questionList.size - 1) onNextScreenType(
                                    CutQuestionnaireScreenType.Question
                                )()
                                else viewModel.setQuestionInd(ind + 1)
                            },
                            text = question.question,
                            defaultValue = defaultValue,
                            onValueChange = { value ->
                                viewModel.setParam(question.question, value)
                            },
                            valuesList = question.options,
                        )
                    }
                }

        when (screenType) {
            CutQuestionnaireScreenType.DateName -> CutQuestionnaireDateScreen(
                onNext = onNextScreenType(CutQuestionnaireScreenType.DateName),
                onBack = onBack,
                onDateChange = { date -> viewModel.setDate(date) },
                date = uiState.date
            )

            CutQuestionnaireScreenType.Question -> {
                when(val state = viewModel.questionnaireUIState) {
                    is QuestionnaireUIState.Success -> if (!screens.isNullOrEmpty()) screens[questionInd]()
                    is QuestionnaireUIState.Loading -> CutQuestionnaireLoadingScreen(
                        onBack = onPrevScreenType(CutQuestionnaireScreenType.Question),
                        title = stringResource(R.string.add_haircut_tab_app_bar),
                    )
                    is QuestionnaireUIState.Error -> CutQuestionnaireErrorScreen(
                        onBack = onPrevScreenType(CutQuestionnaireScreenType.Question),
                        errorMessage = state.message,
                        onRetry = viewModel::requestForm,
                        title = stringResource(R.string.add_haircut_tab_app_bar),
                    )
                }

            }

            CutQuestionnaireScreenType.Client -> {
                CutQuestionnaireClientChoiceScreen(
                    defaultValue = uiState.clientDTO?.getFullName()?:clientDTO?.getFullName()?:"",
                    onBack = onPrevScreenType(CutQuestionnaireScreenType.Client),
                    onNext = { clientDTO ->
                        viewModel.setClientDTO(clientDTO)
                        viewModel.requestCutRecommendations()
                        onNextScreenType(CutQuestionnaireScreenType.Client)()
                    },
                    clients = uiState.clients.collectAsState(mapOf()).value.values.toList()
                        .map { client -> client.value }
                )
            }

            CutQuestionnaireScreenType.Camera -> {
                val ctx = LocalContext.current
                val textWrongUri = stringResource(R.string.wrong_uri)
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val photoUri = result.data?.getStringExtra("photoUri")
                        photoUri?.let {
                            viewModel.setPhotoUri(it.toUri())
                            onNextScreenType(CutQuestionnaireScreenType.Camera)()
                        } ?: ToastNotifier(ctx).show(message = textWrongUri)
                    }
                }
                CameraClaimScreen(
                    onBack = onPrevScreenType(CutQuestionnaireScreenType.Camera),
                    onNext = { launcher.launch(Intent(ctx, CameraActivity::class.java)) },
                )
            }

            CutQuestionnaireScreenType.Choice -> {
                viewModel.setRecentCuts(clientRecentCutIds)

                CutQuestionnaireCutChoiceScreen(
                    onBack = onPrevScreenType(CutQuestionnaireScreenType.Choice),
                    onCutChoice = { cutId: Int ->
                        viewModel.setCutId(cutId)
                        onNextScreenType(CutQuestionnaireScreenType.Choice)()
                    },
                    recentCuts = uiState.recentCuts ?: listOf(),
                    recommendationsUiState = viewModel.recommendationsUIState,
                    cutRecommendations = uiState.cutRecommendations ?: listOf(),
                    onRetry = viewModel::requestCutRecommendations
                )
            }

            CutQuestionnaireScreenType.Confirm -> {
                val ctx = LocalContext.current
                val successMessage = stringResource(R.string.cut_has_been_created)

                CutQuestionnaireConfirmScreen(
                    onBack = onPrevScreenType(CutQuestionnaireScreenType.Confirm),
                    onNext = {
                        val addResult = viewModel.tryAddCut(
                            context = ctx,
                            onAddClick = onAddCutClick,
                        )

                        if (addResult == AddResult.SUCCESS) {
                            ToastNotifier(context = ctx).show(message = successMessage)
                            onBack()
                            viewModel.navigate(CutQuestionnaireScreenType.entries.first())
                            viewModel.resetQuestionInd()
                        } else if (addResult == AddResult.CLIENT_IS_NOT_FOUND) {
                            ToastNotifier(context = ctx).show(message = addResult.toString())
                        }
                    },
                    cutDTO = viewModel.getCut()?.collectAsState()?.value
                )
            }
        }
    }
}