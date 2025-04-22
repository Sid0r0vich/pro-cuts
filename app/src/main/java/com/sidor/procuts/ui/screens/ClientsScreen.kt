package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.ui.LocalBoardPadding
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.TextWithPlusButton
import com.sidor.procuts.ui.screens.items.ClientItem
import com.sidor.procuts.ui.screens.topbars.ClientsTopAppBar


@Composable
fun ClientsScreen(
    onBack: () -> Unit,
    clients: List<ClientDTO>,
    onClientClick: (ClientDTO) -> Unit,
    onAddClientClick: () -> Unit,
    loadingIsCompleted: Boolean
) {
    var searchText by remember { mutableStateOf("") }

    TopAppBarScreen(
        topBar = {
            ClientsTopAppBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onBack = onBack
            )
        },
    ) { LazyPaddingScreen(
        paddingSpaces = PaddingSpaces(2)
    ) {
        item {
            TextWithPlusButton(
                text = stringResource(R.string.clients),
                onClick = onAddClientClick,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

        val filteredClientNameList = clients
            .filter { client -> if (searchText.isNotEmpty()) client.getFullName().lowercase().contains(searchText.lowercase()) else true }

        filteredClientNameList
            .sortedWith (compareBy { it.getFullName() } )
            .forEach { client ->
                item {
                    Spacer(modifier = Modifier.height(LocalBoardPadding.current * 1))
                    ClientItem(
                        name = client.getFullName(),
                        onClick = { onClientClick(client) },
                        loadingIsCompleted = loadingIsCompleted
                    )
                }
            }

        if (filteredClientNameList.isEmpty()) {
            item {
                DefaultSpacer(19)
                Text(
                    text = stringResource(R.string.client_not_found),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
    }
}