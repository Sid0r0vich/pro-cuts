package com.sidor.procuts.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.CutDateDTO
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.TextWithPlusButton
import com.sidor.procuts.ui.screens.cards.ClientCard
import com.sidor.procuts.ui.screens.items.VisitItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(
    clientDTO: ClientDTO?,
    cutDates: List<CutDateDTO>,
    onBack: () -> Unit,
    onVisitClick: (CutDateDTO) -> Unit,
    onAddCutClick: () -> Unit,
    onAddCareClick: () -> Unit,
    onEditClientClick: () -> Unit
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.client_tab_app_bar),
                actions = {
                    IconButton(onClick = onEditClientClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.button_back),
                            tint = LocalColorPalette.current.mainColor
                        )
                    }
                },
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            if (clientDTO != null) {
                item {
                    DefaultSpacer(1)
                    ClientCard(clientDTO)
                }
                item {
                    DefaultSpacer(2)
                    TextWithPlusButton(
                        text = stringResource(R.string.cuts),
                        onClick = onAddCutClick
                    )
                }
                cutDates.forEach { cutDate ->
                    item {
                        DefaultSpacer(1)
                        VisitItem(date = cutDate.date, onClick = { onVisitClick(cutDate) })
                    }
                }
                item {
                    DefaultSpacer(2)
                    TextWithPlusButton(
                        text = stringResource(R.string.cares),
                        onClick = onAddCareClick
                    )
                }
            } else {
                item {
                    Text(text = stringResource(R.string.client_not_found))
                }
            }
        }
    }
}