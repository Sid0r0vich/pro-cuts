package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow

interface CutDateRepository {
    fun getCutStream(cutId: Int): Flow<CutDateDTO?>
    fun getStream(): List<Flow<CutDateDTO>>
    suspend fun loadCuts()
    fun getAllCutsWithClientId(clientId: Int): List<Flow<CutDateDTO>>
    fun insertCut(cutDateInfoDTO: CutDateInfoDTO)
    fun updateCut(cutDateDTO: CutDateDTO): Boolean
    fun deleteCut(cutId: Int)
}