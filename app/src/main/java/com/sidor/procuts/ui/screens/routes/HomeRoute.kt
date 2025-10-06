package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.ClientInfoDTO
import com.sidor.procuts.data.models.CutDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.data.models.defaultUser
import com.sidor.procuts.ui.components.CareForm
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
    val userName = (viewModel.getUser().collectAsState().value ?: defaultUser).name

    when (uiState.screenType) {
        HomeScreenType.Home -> HomeScreen(
            onCardClick = { homeCardScreenType: HomeCardScreenType ->
                when (homeCardScreenType) {
                    HomeCardScreenType.StudyCut -> {}
                    HomeCardScreenType.Clients -> viewModel.navigateClients()
                    HomeCardScreenType.MyCuts -> {}
                    HomeCardScreenType.StartCutting -> viewModel.navigateAddCut()
                }
            },
            userName = userName
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
            ClientScreen(
                clientDTO = uiState.clientDTO,
                onBack = { viewModel.navigateClients() },
                onVisitClick = { cutDateDTO: CutDateDTO ->
                    viewModel.setVisit(cutDateDTO)
                    viewModel.navigateVisit()
                },
                onAddCutClick = {
                    viewModel.navigateAddCut()
                },
                onEditClientClick = {
                    viewModel.navigateEditClient()
                },
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
            clientId = uiState.clientDTO?.id
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
            cut = uiState.cutDateDTO?.let { viewModel.getCutDTO(it.cutId) }
                ?.collectAsState()?.value,
        )
        HomeScreenType.Cut -> CutScreen(
            cut = uiState.cutDTO!!,
            onBack = { viewModel.navigateVisit() }
        )
    }
}