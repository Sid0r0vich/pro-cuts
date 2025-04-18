package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.ui.CareForm
import com.sidor.procuts.ui.screens.AddCareScreen
import com.sidor.procuts.ui.screens.AddClientScreen
import com.sidor.procuts.ui.screens.ClientScreen
import com.sidor.procuts.ui.screens.CutQuestionnaireFirstScreen
import com.sidor.procuts.ui.screens.CutScreen
import com.sidor.procuts.ui.screens.HomeScreen
import com.sidor.procuts.ui.screens.VisitScreen
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
            onClientClick = { client: Client ->
                viewModel.setClient(client)
                viewModel.navigateClient()
            },
            onAddClientClick = { viewModel.navigateAddClient() }
        )
        HomeScreenType.Client -> ClientScreen(
            client = uiState.client,
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
//        HomeScreenType.AddCut -> AddCutScreen(
//            onBack = { viewModel.navigateClient() },
//            onAddCut = { cutForm: CutForm ->
//                viewModel.addCut(cutForm)
//                viewModel.navigateClient()
//            }
//        )
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