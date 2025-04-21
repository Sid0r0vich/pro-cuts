package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.ui.CareForm
import com.sidor.procuts.ui.screens.AddCareScreen
import com.sidor.procuts.ui.screens.AddClientScreen
import com.sidor.procuts.ui.screens.ClientScreen
import com.sidor.procuts.ui.screens.ClientsScreen
import com.sidor.procuts.ui.screens.CutScreen
import com.sidor.procuts.ui.screens.EditClientScreen
import com.sidor.procuts.ui.screens.HomeScreen
import com.sidor.procuts.ui.screens.VisitScreen
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState.screenType) {
        HomeScreenType.Home -> HomeScreen(
            onClientsClick = { viewModel.navigateClients() }
        )
        HomeScreenType.Clients -> ClientsScreen(
            onBack = { viewModel.navigateHome() },
            onClientClick = { client: Client ->
                viewModel.setClient(client)
                viewModel.navigateClient()
            },
            onAddClientClick = { viewModel.navigateAddClient() }
        )
        HomeScreenType.Client -> ClientScreen(
            client = uiState.client,
            onBack = { viewModel.navigateClients() },
            onVisitClick = { (cutId, cutDate) ->
                viewModel.setCutId(cutId)
                viewModel.setVisit(cutDate)
                viewModel.navigateVisit()
            },
            onAddCutClick = {
                viewModel.navigateAddCut()
            },
            onAddCareClick = {
                viewModel.navigateAddCare()
            },
            onEditClientClick = {
                viewModel.navigateEditClient()
            }
        )
        HomeScreenType.AddClient -> AddClientScreen(
            onBack = { viewModel.navigateClients() },
            onAddClient = { client: Client ->
                viewModel.addClient(client)
                viewModel.navigateClients()
            }
        )
        HomeScreenType.EditClient -> EditClientScreen(
            onBack = { viewModel.navigateClient() },
            client = viewModel.getClient(),
            onEditClient = { client: Client ->
                viewModel.addClient(client)
                viewModel.setClient(client)
                viewModel.navigateClient()
            }
        )
        HomeScreenType.AddCut -> CutQuestionnaireRoute(
            onBack = { viewModel.navigateClient() },
        )
        HomeScreenType.AddCare -> AddCareScreen(
            onBack = { viewModel.navigateClient() },
            onAddCare = { careForm: CareForm ->
                // TODO
            }
        )
        HomeScreenType.Visit -> VisitScreen(
            visit = uiState.cutDate!!,
            onBack = { viewModel.navigateClient() },
            onCutClick = { cut: Cut ->
                viewModel.setCut(cut)
                viewModel.navigateCut()
            },
            cutParams = cutDatesList[uiState.cutId]?.cutParams ?: mapOf()
        )
        HomeScreenType.Cut -> CutScreen(
            cut = uiState.cut!!,
            onBack = { viewModel.navigateVisit() }
        )
    }
}