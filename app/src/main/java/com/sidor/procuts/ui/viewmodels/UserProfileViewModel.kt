package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellness.auth.Auth
import com.sidor.procuts.data.UserRepository
import com.sidor.procuts.data.models.UserDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val auth: Auth,
    private val userRepository: UserRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            userRepository.loadUser()
        }
    }

    fun getUser(): StateFlow<UserDTO?> = userRepository.getUserStateFlow()

    fun signOut() = auth.signOut()
}