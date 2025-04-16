package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sidor.procuts.R
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.caresList
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.ui.ClientCard
import com.sidor.procuts.ui.DateItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(
    clientName: String,
    onBack: () -> Unit,
    onCutClick: (Cut) -> Unit
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
                    Text(
                        text = stringResource(R.string.cuts),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge.merge(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                cutDatesList.forEach { date ->
                    item {
                        DefaultSpacer(1)
                        val cutName: String = stringResource(R.string.no_found_cut_name)
                        DateItem(date = date.date, onClick = { onCutClick(allCuts[date.cutId]!!) })
                    }
                }
                item {
                    DefaultSpacer(2)
                    Text(
                        text = stringResource(R.string.cares),
                        fontWeight = FontWeight.Bold,
                        style = typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                caresList.forEach { date ->
                    item {
                        DefaultSpacer(1)
                        DateItem(date = date, onClick = {})
                    }
                }
            }
        }
}