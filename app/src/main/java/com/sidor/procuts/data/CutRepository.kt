package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow

interface CutRepository {
    fun getCutStream(cutId: Int): Flow<CutDTO?>
    fun getAll(): List<CutDTO>
    fun getStream(): List<Flow<CutDTO>>
    fun getCuts(clientIdList: List<Int>): List<Flow<CutDTO>>
    fun insertCut(cutInfoDTO: CutInfoDTO)
    fun updateCut(cutDTO: CutDTO): Boolean
    fun deleteCut(cutId: Int)
}