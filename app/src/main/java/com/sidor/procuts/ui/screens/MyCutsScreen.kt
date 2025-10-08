package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.items.VisitItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.viewmodels.MyCutsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCutsScreen(
    onBack: () -> Unit,
    onVisitClick: (CutDateDTO) -> Unit,
    viewModel: MyCutsViewModel = hiltViewModel()
) {
    val cutDates = viewModel.getCuts().collectAsState().value
        .values
        .map { cutDateStateFlow -> cutDateStateFlow.collectAsState().value }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.my_cuts_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            if (cutDates.isNotEmpty()) {
                cutDates.forEach { cutDate ->
                    item {
                        DefaultSpacer(1)
                        VisitItem(
                            date = cutDate.date, onClick = { onVisitClick(cutDate) },
                            loadingIsCompleted = true
                        )
                    }
                }
            } else {
                item {
                    Text(text = stringResource(R.string.cuts_no_found))
                }
            }
        }
    }
}