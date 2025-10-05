package com.sidor.procuts.ui.components

import java.util.Date

data class CutForm(
    val name: String,
    val date: Date,
    val cutParams: Map<String, String>
)