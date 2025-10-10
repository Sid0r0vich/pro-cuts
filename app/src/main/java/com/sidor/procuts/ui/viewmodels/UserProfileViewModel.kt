package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellness.auth.Auth
import com.sidor.procuts.data.UserRepository
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.ui.screens.screentypes.UserProfileScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val auth: Auth,
    private val userRepository: UserRepository
) : ViewModel() {
    data class UiState(
        val screenType: UserProfileScreenType,
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(UserProfileScreenType.User))
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        viewModelScope.launch {
            userRepository.loadUser()
        }
    }

    fun getUser(): StateFlow<UserDTO?> = userRepository.getUserStateFlow()

    fun editUser(userDTO: UserDTO) {
        viewModelScope.launch {
            userRepository.editUser(userDTO)
        }
    }

    fun signOut() = auth.signOut()

    fun deleteAccount() {
        auth.deleteAccount()
        auth.userId.value?.let {
            viewModelScope.launch {
                userRepository.deleteUser(it)
            }
        }
    }

    fun navigate(screenType: UserProfileScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun navigateUser() = navigate(UserProfileScreenType.User)
    fun navigateEdit() = navigate(UserProfileScreenType.Edit)
}