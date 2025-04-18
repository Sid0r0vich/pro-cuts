package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
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
    client: Client?,
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
            if (client != null) {
                item {
                    DefaultSpacer(1)
                    ClientCard(client)
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
            } else {
                item {
                    Text(text = stringResource(R.string.client_not_found))
                }
            }
        }
    }
}