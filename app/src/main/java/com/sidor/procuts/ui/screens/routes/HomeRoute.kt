package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
        HomeScreenType.Clients -> ClientsScreen(
            onBack = { viewModel.navigateHome() },
            clients = viewModel
                .getAllClients()
                .map { clientFlow -> clientFlow.collectAsState().value },
            onClientClick = { clientDTO: ClientDTO ->
                viewModel.setClient(clientDTO)
                viewModel.navigateClient()
            },
            onAddClientClick = { viewModel.navigateAddClient() }
        )
        HomeScreenType.Client -> ClientScreen(
            clientDTO = viewModel.getClientDTO(),
            cutDates = viewModel
                .getClientCutDates()
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
            }
        )
        HomeScreenType.AddClient -> AddClientScreen(
            onBack = { viewModel.navigateClients() },
            onAddClient = { clientInfoDTO: ClientInfoDTO ->
                viewModel.addClient(clientInfoDTO)
                viewModel.navigateClients()
            }
        )
        HomeScreenType.EditClient -> {
            val clientDTO = viewModel.getClientDTO()
            if (clientDTO != null) {
                EditClientScreen(
                    onBack = { viewModel.navigateClient() },
                    clientDTO = clientDTO,
                    onEditClient = { clientDTO: ClientDTO ->
                        viewModel.editClient(clientDTO)
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
            getClientIdOnPhoneNumber = { phoneNumber: String ->
                viewModel.getClientIdOnPhoneNumber(phoneNumber)
            }
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
            onCutClick = { cut: CutDTO ->
                viewModel.setCut(cut)
                viewModel.navigateCut()
            },
            cutParams = viewModel.getCutDateDTO()?.cutParams ?: mapOf()
        )
        HomeScreenType.Cut -> CutScreen(
            cut = uiState.cut!!,
            onBack = { viewModel.navigateVisit() }
        )
    }
}