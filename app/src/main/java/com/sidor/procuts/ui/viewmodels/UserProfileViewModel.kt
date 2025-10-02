package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.wellness.auth.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val auth: Auth,
) : ViewModel() {
    fun signOut() = auth.signOut()
}