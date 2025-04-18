package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.sidor.procuts.R
import com.sidor.procuts.data.cliensList
import com.sidor.procuts.ui.LocalGridPadding
import com.sidor.procuts.ui.TextWithPlusButton
import com.sidor.procuts.ui.screens.cards.StudyCard
import com.sidor.procuts.ui.screens.items.ClientItem
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClientClick: (String) -> Unit,
    onAddClientClick: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    TopAppBarScreen(
        topBar = { UserTopAppBar(
            searchText = searchText,
            onSearchTextChange = { searchText = it }
        ) },
    ) { LazyPaddingScreen {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    StudyCard(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.study_cut)
                    )
                    Spacer(modifier = Modifier.width(LocalGridPadding.current * 2))
                    StudyCard(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.study_care)
                    )
                }
            }
            item {
                DefaultSpacer(2)
                TextWithPlusButton(
                    text = stringResource(R.string.clients),
                    onClick = onAddClientClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalGridPadding.current * 2),
                )
            }

            val filteredClientNameList = cliensList
                .map { client -> "${client.firstName} ${client.middleName ?: ""} ${client.lastName}" }
                .filter { clientName -> if (searchText.isNotEmpty()) clientName.lowercase().contains(searchText.lowercase()) else true }

            filteredClientNameList
                .sorted()
                .forEach { clientName ->
                    item {
                        Spacer(modifier = Modifier.height(LocalGridPadding.current * 1))
                        ClientItem(
                            modifier = Modifier.padding(horizontal = LocalGridPadding.current * 2),
                            name = clientName,
                            onClick = { onClientClick(clientName) }
                        )
                    }
                }

            if (filteredClientNameList.isEmpty()) {
                item {
                    DefaultSpacer(19)
                    Text(
                        text = stringResource(R.string.client_no_found),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}