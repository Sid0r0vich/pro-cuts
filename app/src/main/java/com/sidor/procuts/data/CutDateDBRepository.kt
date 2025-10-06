package com.sidor.procuts.data

import android.util.Log
import com.example.wellness.auth.Auth
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.network.db.GinApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CutDateDBRepository @Inject constructor(
    private val apiService: GinApiService,
    private val auth: Auth
): CutDateRepository {
    private val cutsStateFlow =
        MutableStateFlow<MutableMap<Int, MutableStateFlow<CutDateDTO>>>(mutableMapOf<Int, MutableStateFlow<CutDateDTO>>())

    override fun getCutStream(cutId: Int): Flow<CutDateDTO?> {
        return cutsStateFlow.value[cutId] ?: flow { emit(null) }
    }

    override fun getStream(): List<Flow<CutDateDTO>> {
        return cutsStateFlow.value.values.toList()
    }

    override suspend fun loadCuts(): Boolean {
        return try {
            val userId = auth.userId.value ?: ""
            cutsStateFlow.value = apiService.getHaircuts(userId).associate { cutDate ->
                cutDate.id to MutableStateFlow(cutDate)
            }.toMutableMap()
            true
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            return false
        }
    }

    override fun getAllCutsWithClientId(clientId: Int): List<StateFlow<CutDateDTO>> {
        return cutsStateFlow.value.values
            .filter {
                cutDateDTO -> cutDateDTO.value.clientId == clientId
            }.toList()
    }

    override suspend fun insertCut(cutDateInfoDTO: CutDateInfoDTO): Boolean {
        return try {
            val cutDTO = apiService.createHaircut(cutDateInfoDTO)
            val updatedMap = cutsStateFlow.value.toMutableMap()
            updatedMap[cutDTO.id] = MutableStateFlow(cutDTO)
            cutsStateFlow.value = updatedMap
            true
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            false
        }
    }

    override suspend fun deleteCut(cutId: Int): Boolean {
        return try {
            apiService.deleteCutDate(cutId)
            val updatedMap = cutsStateFlow.value.toMutableMap()
            updatedMap.remove(cutId)
            cutsStateFlow.value = updatedMap
            true
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            false
        }
    }
}