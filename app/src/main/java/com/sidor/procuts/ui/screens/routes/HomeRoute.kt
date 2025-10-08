package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.CutDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.data.models.defaultUser
import com.sidor.procuts.ui.screens.AddClientScreen
import com.sidor.procuts.ui.screens.ClientScreen
import com.sidor.procuts.ui.screens.ClientsScreen
import com.sidor.procuts.ui.screens.CutScreen
import com.sidor.procuts.ui.screens.EditClientScreen
import com.sidor.procuts.ui.screens.HomeScreen
import com.sidor.procuts.ui.screens.MyCutsScreen
import com.sidor.procuts.ui.screens.VisitScreen
import com.sidor.procuts.ui.screens.screentypes.HomeCardScreenType
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    val userDTO = (viewModel.getUser().collectAsState().value ?: defaultUser)

    when (uiState.screenType) {
        HomeScreenType.Home -> HomeScreen(
            onCardClick = { homeCardScreenType: HomeCardScreenType ->
                when (homeCardScreenType) {
                    HomeCardScreenType.Clients -> viewModel.navigateClients()
                    HomeCardScreenType.StartCutting -> viewModel.navigateAddCut()
                    HomeCardScreenType.MyCuts -> viewModel.navigateCuts()
                }
            },
            userDTO = userDTO
        )
        HomeScreenType.Clients -> {
            ClientsScreen(
                onBack = viewModel::navigateBack,
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
                onBack = viewModel::navigateBack,
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
            onBack = viewModel::navigateBack,
        )
        HomeScreenType.EditClient -> {
            val clientDTO = uiState.clientDTO
            if (clientDTO != null) {
                EditClientScreen(
                    onBack = viewModel::navigateBack,
                    clientDTO = clientDTO,
                    onEditClient = { clientDTO: ClientDTO ->
                        viewModel.setClient(clientDTO)
                        viewModel.navigateBack()
                    }
                )
            }
            else viewModel.navigateAddClient()
        }
        HomeScreenType.AddCut -> CutQuestionnaireRoute(
            onBack = viewModel::navigateBack,
            onAddCutClick = { cutDateInfoDTO: CutDateInfoDTO ->
                viewModel.addCutDate(cutDateInfoDTO)
            },
            clientDTO = uiState.clientDTO
        )
        HomeScreenType.Visit -> VisitScreen(
            visit = uiState.cutDateDTO,
            onBack = viewModel::navigateBack,
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
            onBack = viewModel::navigateBack,
        )

        HomeScreenType.MyCuts -> MyCutsScreen(
            onBack = viewModel::navigateBack,
            onVisitClick = { cutDateDTO: CutDateDTO ->
                viewModel.setVisit(cutDateDTO)
                viewModel.navigateVisit()
            },
        )
    }
}