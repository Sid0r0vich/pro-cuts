package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.caresList
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.ui.TextWithPlusButton
import com.sidor.procuts.ui.screens.cards.ClientCard
import com.sidor.procuts.ui.screens.items.VisitItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(
    clientName: String,
    onBack: () -> Unit,
    onVisitClick: (CutDate) -> Unit,
    onAddCutClick: () -> Unit,
    onAddCareClick: () -> Unit
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.client_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            horizontalSpaceCount = 4,
            verticalSpaceCount = 2,
        ) {
            item {
                DefaultSpacer(1)
                ClientCard(clientName)
            }
            item {
                DefaultSpacer(2)
                TextWithPlusButton(
                    text = stringResource(R.string.cuts),
                    onClick = onAddCutClick
                )
            }
            cutDatesList.forEach { date ->
                item {
                    DefaultSpacer(1)
                    VisitItem(date = date.date, onClick = { onVisitClick(date) })
                }
            }
            item {
                DefaultSpacer(2)
                TextWithPlusButton(
                    text = stringResource(R.string.cares),
                    onClick = onAddCareClick
                )
            }
            caresList.forEach { date ->
                item {
                    DefaultSpacer(1)
                    VisitItem(date = date, onClick = {})
                }
            }
        }
    }
}