package com.sidor.procuts.data

import java.util.Date

data class CutDateDTO(
    val id: Int = -1,
    val cutId: Int = -1,
    val date: Date = Date(),
    val cutParams: Map<String, String> = mutableMapOf()
)

data class CutDTO(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int
)

class CutDateInfoDTO(
    val cutId: Int,
    val date: Date,
    val cutParams: Map<String, String>
) {
    fun withId(id: Int): CutDateDTO =
        CutDateDTO(
            id = id,
            cutId = cutId,
            date = date,
            cutParams = cutParams
        )
}