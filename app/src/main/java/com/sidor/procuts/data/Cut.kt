package com.sidor.procuts.data

import android.net.Uri
import com.sidor.procuts.R
import java.util.Date

data class CutDTO(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int
)

class CutInfoDTO(
    val name: String,
    val description: String,
    val imageId: Int
) {
    fun withId(id: Int): CutDTO =
        CutDTO(
            id = id,
            name = name,
            description = description,
            imageId = imageId
        )
}

data class CutDateDTO(
    val id: Int,
    val cutId: Int,
    val clientId: Int,
    val date: Date,
    val cutPhoto: Uri?,
    val cutParams: Map<String, String> = mutableMapOf()
)

class CutDateInfoDTO(
    val cutId: Int,
    val clientId: Int,
    val date: Date,
    val cutPhoto: Uri?,
    val cutParams: Map<String, String>,
) {
    fun withId(id: Int): CutDateDTO =
        CutDateDTO(
            id = id,
            cutId = cutId,
            clientId = clientId,
            date = date,
            cutPhoto = cutPhoto,
            cutParams = cutParams
        )
}

val defaultCutDateDTO = CutDateDTO(
    id = 0,
    cutId = 0,
    clientId = 0,
    date = Date(),
    cutPhoto = null,
    cutParams = mapOf()
)

val defaultCutDTO = CutDTO(
    id = -1,
    name = "Cut not found",
    description = "",
    imageId = R.drawable.default_user_photo
)