package com.sidor.procuts.data

import android.util.Log
import com.example.wellness.auth.Auth
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.network.db.GinApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDBRepository @Inject constructor(
    private val apiService: GinApiService,
    private val auth: Auth
): UserRepository {
    private val userStateFlow: MutableStateFlow<UserDTO?> = MutableStateFlow(null)

    override suspend fun loadUser(): Boolean {
        return try {
            userStateFlow.value = auth.userId.value?.let { apiService.getUser(it) }
            true
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            false
        }
    }
    override fun getUserStateFlow(): StateFlow<UserDTO?> = userStateFlow

    override suspend fun insertUser(userDTO: UserDTO): Boolean {
        return try {
            apiService.createUser(userDTO)
            true
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            false
        }
    }
}