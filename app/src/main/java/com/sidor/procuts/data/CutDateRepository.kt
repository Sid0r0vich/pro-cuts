package com.sidor.procuts.data

import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CutDateRepository {
    fun getCutStream(cutId: Int): StateFlow<CutDateDTO>?
    fun getStream(): MutableStateFlow<MutableMap<Int, MutableStateFlow<CutDateDTO>>>
    suspend fun loadCuts(): Boolean
    fun getAllCutsWithClientId(clientId: Int): List<StateFlow<CutDateDTO>>
    suspend fun insertCut(cutDateInfoDTO: CutDateInfoDTO): Boolean
    suspend fun deleteCut(cutId: Int): Boolean
}