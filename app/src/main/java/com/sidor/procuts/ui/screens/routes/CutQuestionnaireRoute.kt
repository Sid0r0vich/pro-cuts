package com.sidor.procuts.ui.screens.routes

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.CameraActivity
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.cutQuestionnaireScreenInfoLists
import com.sidor.procuts.network.defaultFeatures
import com.sidor.procuts.ui.ToastNotifier
import com.sidor.procuts.ui.screens.CameraClaimScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireChoiceScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireConfirmScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutQuestionnairePhoneNumberScreen
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
    viewModel: CutQuestionnaireViewModel = hiltViewModel(),
    getClientRecentCutIds: @Composable (Int) -> List<Int>,
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

            CutQuestionnaireScreenType.PhoneNumber -> CutQuestionnairePhoneNumberScreen(
                onBack = onPrevQuestion(CutQuestionnaireScreenType.PhoneNumber),
                onNext = { phoneNumber: String ->
                    viewModel.setPhoneNumber(phoneNumber)
                    val clientId = getClientIdOnPhoneNumber(phoneNumber)
                    viewModel.setClientId(clientId)

                    if (clientId != null) onNextQuestion(CutQuestionnaireScreenType.PhoneNumber)()
                    else {
                        phoneNumberIsExists = false
                    }
                },
                phoneNumber = uiState.clientPhoneNumber ?: "8888888888",
                phoneNumberIsExists = phoneNumberIsExists,
                onSetPhoneNumber = { phoneNumber: String ->
                    viewModel.setPhoneNumber(phoneNumber)
                    phoneNumberIsExists = true
                },
            )
            CutQuestionnaireScreenType.Camera -> {
                val clientRecentCuts = viewModel.getClientId()?.let { getClientRecentCutIds(it) }
                val onNext = {
                    viewModel.getCutRecommendations(defaultFeatures)
                    clientRecentCuts?.let { viewModel.setRecentCuts(it) }
                    onNextQuestion(CutQuestionnaireScreenType.Camera)()
                }
                val ctx = LocalContext.current
                val textWrongUri = stringResource(R.string.wrong_uri)
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val photoUri = result.data?.getStringExtra("photoUri")
                        photoUri?.let {
                            viewModel.setPhotoUri(it.toUri())
                            onNext()
                        } ?: ToastNotifier(ctx).show(message = textWrongUri)
                    }
                }
                CameraClaimScreen(
                    onBack = onPrevQuestion(CutQuestionnaireScreenType.Camera),
                    onNext = { launcher.launch(Intent(ctx, CameraActivity::class.java)) },
                )
            }


            CutQuestionnaireScreenType.Choice -> {
                CutQuestionnaireChoiceScreen(
                    onBack = onPrevQuestion(CutQuestionnaireScreenType.Choice),
                    onCutChoice = { cutId: Int ->
                        viewModel.setCutId(cutId)
                        onNextQuestion(CutQuestionnaireScreenType.Choice)()
                    },
                    recentCuts = viewModel.getRecentCuts() ?: listOf(),
                    recommendationsUiState = viewModel.recommendationsUiState,
                    cutRecommendations = viewModel.getCutRecommendations() ?: listOf()
                )
            }

            CutQuestionnaireScreenType.Confirm -> {
                val ctx = LocalContext.current
                val successMessage = stringResource(R.string.cut_has_been_created)

                CutQuestionnaireConfirmScreen(
                    onBack = onPrevQuestion(CutQuestionnaireScreenType.Confirm),
                    onNext = {
                        val addResult = viewModel.tryAddCut(
                            getClientIdOnPhoneNumber = getClientIdOnPhoneNumber,
                            onAddClick = onAddCutClick,
                        )

                        if (addResult == AddResult.SUCCESS) {
                            ToastNotifier(context = ctx).show(message = successMessage)
                            onBack()
                            onNextQuestion(CutQuestionnaireScreenType.PhoneNumber)()
                        } else if (addResult == AddResult.PHONE_NUMBER_IS_NOT_FOUND) {
                            ToastNotifier(context = ctx).show(message = addResult.toString())
                        }
                    },
                    cut = viewModel.getCut()?.collectAsState()?.value
                )
            }

            else -> {
                val index = screenType.ordinal - 1
                screens[index]()
            }
        }
    }
}