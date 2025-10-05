package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow

interface CutDateRepository {
    fun getCutStream(cutId: Int): Flow<CutDateDTO?>
    fun getStream(): List<Flow<CutDateDTO>>
    suspend fun loadCuts()
    fun getAllCutsWithClientId(clientId: Int): List<Flow<CutDateDTO>>
    suspend fun insertCut(cutDateInfoDTO: CutDateInfoDTO): Boolean
    suspend fun deleteCut(cutId: Int): Boolean
}