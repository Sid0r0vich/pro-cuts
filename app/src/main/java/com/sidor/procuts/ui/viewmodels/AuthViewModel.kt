package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellness.auth.Auth
import com.example.wellness.auth.AuthData
import com.example.wellness.auth.AuthStatus
import com.sidor.procuts.auth.UserInfo
import com.sidor.procuts.data.UserRepository
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.utils.DataValidator
import com.sidor.procuts.utils.toAuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: Auth,
    private val userRepository: UserRepository
): ViewModel() {
    val uiState = auth.authStateFlow

    fun signUp(userInfo: UserInfo, onComplete: (AuthStatus) -> Unit = {}) {
        val authData = AuthData(userInfo.email, userInfo.password)
        DataValidator.validateLoginDataWithStatus(authData)
            .toAuthStatus()
            .also { if (it != AuthStatus.SUCCESS) { onComplete(it); return@signUp } }

        auth.signUp(authData) { status ->
            if (status == AuthStatus.SUCCESS) {
                viewModelScope.launch {
                    auth.userId.value?.let {
                        userRepository.insertUser(
                            UserDTO(
                                id = it,
                                name = userInfo.name,
                                photo = null
                            )
                        )
                    }
                }

                onComplete(status)
            }
        }
    }

    fun signIn(authData: AuthData, onComplete: (AuthStatus) -> Unit = {}) {
        DataValidator.validateLoginDataWithStatus(authData)
            .toAuthStatus()
            .also { if (it != AuthStatus.SUCCESS) { onComplete(it); return@signIn } }

        auth.signIn(authData, onComplete)
    }
}