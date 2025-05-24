package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.CutOptionCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireChoiceScreen(
    onBack: () -> Unit,
    onCutChoice: (Int) -> Unit,
    clientRecentCutIds: List<Int>,
    allCuts: List<StateFlow<CutDTO>>
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.cut_choice_tab_app_bar),
                onBack = onBack
            )
        },
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
            allCuts.toList().forEach { optionFlow ->
                item {
                    val option = optionFlow.collectAsState().value
                    CutOptionCard(
                        cutOption = option,
                        onClick = { onCutChoice(option.id) }
                    )
                }
            }
            if (clientRecentCutIds.isNotEmpty()) {
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
            clientRecentCutIds.map { id -> allCuts[id] }
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
}