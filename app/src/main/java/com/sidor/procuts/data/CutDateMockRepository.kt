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
        cutDatesMap.map { (_, client) ->
            flow { emit(client) }
        }

    override fun getAllCutsWithClientId(clientId: Int): List<Flow<CutDateDTO>> =
        cutDatesMap
            .filter { (_, cutDate) -> cutDate.clientId == clientId }
            .map { (_, cutDate) ->
                flow { emit(cutDate) }
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
            Triple("31-12-2022", 0, 12),
            Triple("31-12-2023", 1, 0),
            Triple("28-11-2024", 1, 4),
            Triple("19-04-2025", 1, 7),
            Triple("19-06-2025", 1, 8)
        )
            .map { triple -> Triple(DMYDateFormat.parse(triple.first)!!, triple.second, triple.third) }
            .withIndex()
            .associate { (index, triple) -> index to
                    CutDateDTO(
                        id = index,
                        cutId = triple.third,
                        clientId = triple.second,
                        date = triple.first,
                        cutPhoto = null,
                        cutParams = mapOf()
                    )
            }
            .toMutableMap()
    }
}