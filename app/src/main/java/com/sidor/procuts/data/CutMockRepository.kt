package com.sidor.procuts.data

import com.sidor.procuts.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CutMockRepository @Inject constructor(): CutRepository {
    override fun getCutStream(cutId: Int): Flow<CutDTO?> =
        flow {
            emit(allCuts[cutId])
        }

    override fun getAll(): List<CutDTO> = allCuts.map { (_, cut) -> cut }

    override fun getStream(): List<Flow<CutDTO>> =
        allCuts.map { (_, cut) ->
            flow { emit(cut) }
        }

    override fun getCuts(cutIdList: List<Int>): List<Flow<CutDTO>> =
        cutIdList.mapNotNull { cutId -> allCuts[cutId] }
            .map { cut ->
                flow { emit(cut) }
            }

    override fun insertCut(cutInfoDTO: CutInfoDTO) {
        val cutId = allCuts.size
        allCuts[cutId] = cutInfoDTO.withId(cutId)
    }

    override fun updateCut(cutDTO: CutDTO): Boolean {
        if (!allCuts.contains(cutDTO.id)) {
            return false
        }

        allCuts[cutDTO.id] = cutDTO
        return true
    }

    override fun deleteCut(clientId: Int) { TODO() }

    companion object {
        val allCuts = mapOf(
            0 to CutDTO(0, "Classic", "Классическая модельная стрижка представляет собой длину волос около пяти сантиметров, при этом присутствуют в основном ровные линии. Такая стрижка выглядит сдержанно и аккуратно, отлично подходит деловым мужчинам и тем, которые не хотят чрезмерного внимания к своему внешнему виду.", R.drawable.classic),
            1 to CutDTO(1, "Undercut", "Для стрижки характерна почти одинаковая длина волос по всей поверхности головы. Классический варианта – это выбритые виски и немного удлиненные волосы на верхней части. Андеркат в таком исполнении подходит практически всем. Он придаст образу мужественности и делового стиля.", R.drawable.undercut),
            2 to CutDTO(2, "Curtains", "Мужская стрижка, предполагающая прямой центральный пробор и симметричные пряди по обеим сторонам. При этом длина стрижки, наличие или отсутствие челки, градуировка никаким образом не регламентированы — здесь у нас с вами полная свобода.", R.drawable.curtains),
            3 to CutDTO(3, "Bold", "Цель данной стрижки — полностью обнажить кожу головы, сбрив все волосы. Стрижка под 0 способствует созданию образа уверенного и сильного человека, своему обладателю придает максимум мужественности, помогает почувствовать себя увереннее и подчеркнуть индивидуальность.", R.drawable.bold),
        ).toMutableMap()

        var cutNamesToId = allCuts.entries.associate { (key, value) -> value.name to key }
    }
}