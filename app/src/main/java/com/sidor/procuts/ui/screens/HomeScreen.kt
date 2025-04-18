package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.cliensList
import com.sidor.procuts.ui.CareForm
import com.sidor.procuts.ui.CutForm
import com.sidor.procuts.ui.TextWithPlusButton
import com.sidor.procuts.ui.screens.items.ClientItem
import com.sidor.procuts.ui.LocalGridPadding
import com.sidor.procuts.ui.screens.cards.StudyCard
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar
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
            },
            onAddClientClick = { viewModel.navigateAddClient() }
        )
        HomeScreenType.Client -> ClientScreen(
            clientName = uiState.clientName ?: stringResource(R.string.default_client_name),
            onBack = { viewModel.navigateHome() },
            onVisitClick = { cutDate: CutDate ->
                viewModel.setVisit(cutDate)
                viewModel.navigateVisit()
            },
            onAddCutClick = {
                viewModel.navigateAddCut()
            },
            onAddCareClick = {
                viewModel.navigateAddCare()
            }
        )
        HomeScreenType.AddClient -> AddClientScreen(
            onBack = { viewModel.navigateHome() },
            onAddClient = {
                client: Client -> viewModel.addClient(client)
                viewModel.navigateHome()
            }
        )
        HomeScreenType.AddCut -> AddCutScreen(
            onBack = { viewModel.navigateClient() },
            onAddCut = { cutForm: CutForm ->
                viewModel.addCut(cutForm)
                viewModel.navigateClient()
            }
        )
        HomeScreenType.AddCare -> AddCareScreen(
            onBack = { viewModel.navigateClient() },
            onAddCare = { careForm: CareForm ->
                // TODO
            }
        )
        HomeScreenType.Visit -> VisitScreen(
            visit = uiState.visit!!,
            onBack = { viewModel.navigateClient() },
            onCutClick = { cut: Cut ->
                viewModel.setCut(cut)
                viewModel.navigateCut()
            }
        )
        HomeScreenType.Cut -> CutScreen(
            cut = uiState.cut!!,
            onBack = { viewModel.navigateVisit() }
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClientClick: (String) -> Unit,
    onAddClientClick: () -> Unit
) {
    TopAppBarScreen(
        topBar = { UserTopAppBar() },
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

            cliensList.map { client ->
                val clientName: String = "${client.firstName} ${client.middleName ?: ""} ${client.lastName}"
                item {
                    Spacer(modifier = Modifier.height(LocalGridPadding.current * 1))
                    ClientItem(
                        modifier = Modifier.padding(horizontal = LocalGridPadding.current * 2),
                        name = clientName,
                        onClick = { onClientClick(clientName) }
                    )
                }
            }
        }
    }
}