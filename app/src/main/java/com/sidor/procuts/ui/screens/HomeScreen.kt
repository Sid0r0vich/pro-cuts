package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.defaultCliensList
import com.sidor.procuts.ui.ClientItem
import com.sidor.procuts.ui.LocalGridPadding
import com.sidor.procuts.ui.StudyCard
import com.sidor.procuts.ui.viewmodels.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState.screenType) {
        HomeScreenType.Home -> HomeScreen(
            modifier = modifier,
            onClientClick = { clientName: String ->
                viewModel.setClientName(clientName)
                viewModel.navigateClient()
            }
        )
        HomeScreenType.Client -> ClientScreen(
            title = uiState.clientName ?: stringResource(R.string.default_client_name),
            onBack = { viewModel.navigateHome() }
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClientClick: (String) -> Unit,
) {
    TopAppBarScreen(
        topBar = { UserAppBar() },
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
                Text(
                    text = stringResource(R.string.clients),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalGridPadding.current * 2),
                )
            }

            defaultCliensList.map { client ->
                item {
                    Spacer(modifier = Modifier.height(LocalGridPadding.current * 2))
                    ClientItem(
                        modifier = Modifier.padding(horizontal = LocalGridPadding.current * 2),
                        text = client,
                        onClick = { onClientClick(client) }
                    )
                }
            }
        }
    }
}