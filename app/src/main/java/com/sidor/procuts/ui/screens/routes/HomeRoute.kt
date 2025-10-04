package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.ClientInfoDTO
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.data.CutDateDTO
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.ui.CareForm
import com.sidor.procuts.ui.screens.AddCareScreen
import com.sidor.procuts.ui.screens.AddClientScreen
import com.sidor.procuts.ui.screens.ClientScreen
import com.sidor.procuts.ui.screens.ClientsScreen
import com.sidor.procuts.ui.screens.CutScreen
import com.sidor.procuts.ui.screens.EditClientScreen
import com.sidor.procuts.ui.screens.HomeScreen
import com.sidor.procuts.ui.screens.VisitScreen
import com.sidor.procuts.ui.screens.screentypes.HomeCardScreenType
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.map

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState.screenType) {
        HomeScreenType.Home -> HomeScreen(
            onCardClick = { homeCardScreenType: HomeCardScreenType ->
                when(homeCardScreenType) {
                    HomeCardScreenType.StudyCut -> {}
                    HomeCardScreenType.Clients -> viewModel.navigateClients()
                    HomeCardScreenType.MyCuts -> {}
                    HomeCardScreenType.StartCutting -> viewModel.navigateAddCut()
                }
            }
        )
        HomeScreenType.Clients -> {
            ClientsScreen(
                onBack = { viewModel.navigateHome() },
                onClientClick = { clientDTO: ClientDTO ->
                    viewModel.setClient(clientDTO)
                    viewModel.navigateClient()
                },
                onAddClientClick = { viewModel.navigateAddClient() },
            )
        }
        HomeScreenType.Client -> {
            var loadingIsCompleted by rememberSaveable { mutableStateOf(false) }

            ClientScreen(
                clientDTO = uiState.clientDTO,
                cutDates = viewModel
                    .getClientCutDates { loadingIsCompleted = true }
                    .map { cutDateStateFlow -> cutDateStateFlow.collectAsState().value },
                onBack = { viewModel.navigateClients() },
                onVisitClick = { cutDateDTO: CutDateDTO ->
                    viewModel.setVisit(cutDateDTO)
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
                },
                loadingIsCompleted = loadingIsCompleted
            )
        }
        HomeScreenType.AddClient -> AddClientScreen(
            onBack = { viewModel.navigateClients() },
            onAddClient = { clientInfoDTO: ClientInfoDTO ->
                viewModel.navigateClients()
            }
        )
        HomeScreenType.EditClient -> {
            val clientDTO = uiState.clientDTO
            if (clientDTO != null) {
                EditClientScreen(
                    onBack = { viewModel.navigateClient() },
                    clientDTO = clientDTO,
                    onEditClient = { clientDTO: ClientDTO ->
                        TODO()
                        //viewModel.editClient(clientDTO)
                        viewModel.setClient(clientDTO)
                        viewModel.navigateClient()
                    }
                )
            }
            else viewModel.navigateAddClient()
        }
        HomeScreenType.AddCut -> CutQuestionnaireRoute(
            onBack = { viewModel.navigateHome() },
            onAddCutClick = { cutDateInfoDTO: CutDateInfoDTO ->
                viewModel.addCutDate(cutDateInfoDTO)
            },
            getClientRecentCutIds = { clientId: Int ->
                viewModel.getClientCutDates(clientId)
                    .map { cutDateStateFlow -> cutDateStateFlow.collectAsState().value }
                    .map { cutDateDTO: CutDateDTO -> cutDateDTO.cutId }
                    .distinct()
            },
        )
        HomeScreenType.AddCare -> AddCareScreen(
            onBack = { viewModel.navigateClient() },
            onAddCare = { careForm: CareForm ->
                // TODO
            }
        )
        HomeScreenType.Visit -> VisitScreen(
            visit = uiState.cutDateDTO,
            onBack = { viewModel.navigateClient() },
            onCutClick = { cut: CutDTO ->
                viewModel.setCut(cut)
                viewModel.navigateCut()
            },
            cutParams = uiState.cutDateDTO?.cutParams ?: mapOf(),
            cut = uiState.cutDateDTO?.let { viewModel.getCutDTO(it.cutId) }?.collectAsState()?.value
        )
        HomeScreenType.Cut -> CutScreen(
            cut = uiState.cutDTO!!,
            onBack = { viewModel.navigateVisit() }
        )
    }
}