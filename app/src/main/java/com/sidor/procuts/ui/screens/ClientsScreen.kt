package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.ui.components.LocalBoardPadding
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.components.TextWithPlusButton
import com.sidor.procuts.ui.screens.items.ClientItem
import com.sidor.procuts.ui.screens.topbars.ClientsTopAppBar
import com.sidor.procuts.ui.viewmodels.ClientsViewModel
import com.sidor.procuts.utils.filterClients


@Composable
fun ClientsScreen(
    onBack: () -> Unit,
    onClientClick: (ClientDTO) -> Unit,
    onAddClientClick: () -> Unit,
    viewModel: ClientsViewModel = hiltViewModel()
) {
    val clients by viewModel.clients.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var loadingIsCompleted by rememberSaveable { mutableStateOf(true) }
    val collectedClients = clients.values.map { client -> client.collectAsState(initial = ClientDTO()).value }

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

        val filteredClientNameList = filterClients(
            clients = collectedClients,
            searchText = searchText
        )

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