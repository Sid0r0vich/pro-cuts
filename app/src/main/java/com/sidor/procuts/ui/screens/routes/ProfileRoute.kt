package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.data.models.defaultPerson
import com.sidor.procuts.ui.screens.EditUserProfileScreen
import com.sidor.procuts.ui.screens.UserProfileScreen
import com.sidor.procuts.ui.screens.screentypes.UserProfileScreenType
import com.sidor.procuts.ui.viewmodels.UserProfileViewModel

@Composable
fun UserProfileRoute(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val personDTO = viewModel.getUser().collectAsState().value?.toPersonDTO() ?: defaultPerson
    
    when (uiState.screenType) {
        UserProfileScreenType.User ->
            UserProfileScreen(
                onSignOut = viewModel::signOut,
                personDTO = personDTO,
                onEditClick = viewModel::navigateEdit
            )

        UserProfileScreenType.Edit ->
            EditUserProfileScreen(
                onBack = viewModel::navigateUser,
                personDTO = personDTO
            )
    }
}