package com.sidor.procuts.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.data.models.defaultUser
import com.sidor.procuts.ui.screens.EditUserProfileScreen
import com.sidor.procuts.ui.screens.UserProfileScreen
import com.sidor.procuts.ui.screens.screentypes.UserProfileScreenType
import com.sidor.procuts.ui.viewmodels.UserProfileViewModel

@Composable
fun UserProfileRoute(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val userDTO = viewModel.getUser().collectAsState().value ?: defaultUser
    
    when (uiState.screenType) {
        UserProfileScreenType.User ->
            UserProfileScreen(
                onSignOut = viewModel::signOut,
                personDTO = userDTO.toPersonDTO(),
                onEditClick = viewModel::navigateEdit,
                onDeleteClick = {
                    viewModel.deleteAccount()
                }
            )

        UserProfileScreenType.Edit ->
            EditUserProfileScreen(
                onBack = viewModel::navigateUser,
                userDTO = userDTO,
                onSaveClick = { userDTO ->
                    viewModel.editUser(userDTO)
                    viewModel.navigateUser()
                }
            )
    }
}