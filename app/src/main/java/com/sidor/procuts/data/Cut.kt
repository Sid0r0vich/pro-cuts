package com.sidor.procuts.data

import java.util.Date

data class CutDate(
    val cutId: Int,
    val date: Date,
    val cutParams: Map<String, String>
)

data class Cut(
    val id: Int,
    val name: String,
    val description: String,
    val imgId: Int
)