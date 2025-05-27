package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow

interface CutRepository {
    fun getCutStream(cutId: Int): Flow<CutDTO?>
    fun getAll(): List<CutDTO>
    fun getStream(): List<Flow<CutDTO>>
    fun getCutsByIds(cutIds: List<Int>): List<Flow<CutDTO>>
    fun getCutsByNames(cutNames: List<String>): List<Flow<CutDTO>>
}