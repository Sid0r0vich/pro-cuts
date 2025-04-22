package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CutDateMockRepository @Inject constructor(): CutDateRepository {
    override fun getCutStream(cutId: Int): Flow<CutDateDTO?> =
        flow {
            emit(cutDatesMap[cutId])
        }

    override fun getStream(): List<Flow<CutDateDTO>> =
        cutDatesMap.map { (id, client) ->
            flow { emit(client) }
        }

    override fun insertCut(cutDateInfoDTO: CutDateInfoDTO) {
        val cutId = cutDatesMap.size
        cutDatesMap[cutId] = cutDateInfoDTO.withId(cutId)
    }

    override fun updateCut(cutDateDTO: CutDateDTO): Boolean {
        if (!cutDatesMap.contains(cutDateDTO.cutId)) {
            return false
        }

        cutDatesMap[cutDateDTO.cutId] = cutDateDTO
        return true
    }

    override fun deleteCut(clientId: Int) { TODO() }

    companion object {
        private var cutDatesMap = mutableListOf(
            "31-12-2022",
            "31-12-2023",
            "28-11-2024",
            "19-04-2025"
        )
            .map { date -> DMYDateFormat.parse(date)!! }
            .withIndex()
            .associate { (index, date) -> index to CutDateDTO(index, allCuts.toList().random().second.id, date, mapOf()) }
            .toMutableMap()
    }
}