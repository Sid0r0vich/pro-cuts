package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.CutOptionCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutChoiceScreen(
    onBack: () -> Unit,
    onCutChoice: (String) -> Unit
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
                    text = R.string.cut_recomendations,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
            cutOptions.forEach { option ->
                item {
                    CutOptionCard(
                        cutOption = option,
                        onClick = { onCutChoice(option.name) }
                    )
                }
            }
        }
    }
}

val cutOptions = allCuts.values.toList()