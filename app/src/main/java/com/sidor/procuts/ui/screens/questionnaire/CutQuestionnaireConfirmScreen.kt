package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.models.CutDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.LazyPaddingScreenWithBottomButtons
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.cards.CutCard
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette


@Composable
fun CutQuestionnaireConfirmScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    cutDTO: CutDTO?
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.confirm_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        cutDTO?.let { cut ->
            LazyPaddingScreenWithBottomButtons(
                buttons = {
                    Button(
                        onClick = onNext,
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LocalColorPalette.current.buttonColor
                        )
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(R.string.choose_cut))
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.padding(start = 4.dp),
                            )
                        }
                    }
                },
                paddingSpaces = PaddingSpaces(horizontal = 2, top = 2, bottom = 1),
            ) {
                item { CutCard(cut) }
            }
        } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_cut_found),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
    }
}