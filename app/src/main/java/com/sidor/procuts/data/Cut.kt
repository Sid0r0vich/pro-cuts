package com.sidor.procuts.data

import java.util.Date

data class CutDate(
    val cutId: Int,
    val date: Date,
)

data class Cut(
    val cutId: Int,
    val cutName: String,
    val cutImg: Int
)