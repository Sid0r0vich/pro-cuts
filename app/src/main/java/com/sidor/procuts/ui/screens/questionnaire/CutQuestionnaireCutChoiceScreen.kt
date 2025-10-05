package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.screens.LazyPaddingScreen
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.cards.CutOptionCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.viewmodels.CutQuestionnaireViewModel
import com.sidor.procuts.ui.viewmodels.RecommendationsUIState
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireCutChoiceScreen(
    onBack: () -> Unit,
    onCutChoice: (Int) -> Unit,
    recommendationsUiState: RecommendationsUIState,
    cutRecommendations: List<StateFlow<CutDTO>>,
    recentCuts: List<StateFlow<CutDTO>>,
    onRetry: () -> Unit,
    viewModel: CutQuestionnaireViewModel = hiltViewModel()
) {
    val topAppBarBarTitle = stringResource(R.string.cut_choice_tab_app_bar)

    when(recommendationsUiState) {
        is RecommendationsUIState.Success ->
            TopAppBarScreen(
                topBar = {
                    TitleTopAppBar(
                        title = topAppBarBarTitle,
                        onBack = onBack
                    )
                },
            ) {
                CutQuestionnaireChoiceScreenContent(
                    onCutChoice = onCutChoice,
                    recommendationsUiState = recommendationsUiState,
                    cutRecommendations = cutRecommendations,
                    recentCuts = recentCuts
                )
            }

        is RecommendationsUIState.Loading -> CutQuestionnaireLoadingScreen(
            onBack = onBack,
            title = topAppBarBarTitle
        )
        is RecommendationsUIState.Error -> CutQuestionnaireErrorScreen(
            errorMessage = recommendationsUiState.message,
            onRetry = onRetry,
            onBack = onBack,
            title = topAppBarBarTitle
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireChoiceScreenContent(
    onCutChoice: (Int) -> Unit,
    recommendationsUiState: RecommendationsUIState,
    cutRecommendations: List<StateFlow<CutDTO>>,
    recentCuts: List<StateFlow<CutDTO>>,
) {
    LazyPaddingScreen(
        paddingSpaces = PaddingSpaces(2)
    ) {
        item {
            Text(
                text = stringResource(R.string.cut_recommendations),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            DefaultSpacer(1)
        }
        when (recommendationsUiState) {
            is RecommendationsUIState.Success ->
                cutRecommendations
                    .forEach { optionFlow ->
                        item {
                            val option = optionFlow.collectAsState().value
                            CutOptionCard(
                                cutOption = option,
                                onClick = { onCutChoice(option.id) }
                            )
                        }
                    }

            else -> {}
        }
        if (recentCuts.isNotEmpty()) {
            item {
                DefaultSpacer(2)
                Text(
                    text = stringResource(R.string.recent_cuts),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
                DefaultSpacer(1)
            }
        }
        recentCuts
            .forEach { optionFlow ->
                item {
                    val option = optionFlow.collectAsState().value
                    CutOptionCard(
                        cutOption = option,
                        onClick = { onCutChoice(option.id) }
                    )
                }
            }
    }
}