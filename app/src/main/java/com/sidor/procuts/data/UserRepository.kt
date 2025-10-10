package com.sidor.procuts.data

import com.sidor.procuts.data.models.UserDTO
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun loadUser(): Boolean
    fun getUserStateFlow(): StateFlow<UserDTO?>
    suspend fun insertUser(userDTO: UserDTO): Boolean
    suspend fun editUser(userDTO: UserDTO): Boolean
    suspend fun deleteUser(userId: String): Boolean
}