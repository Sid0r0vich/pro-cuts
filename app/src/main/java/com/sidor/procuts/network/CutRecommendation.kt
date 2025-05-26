package com.sidor.procuts.network

import kotlinx.serialization.Serializable

@Serializable
data class CutRecommendation(
    val cutId: Int,
    val prob: Float
)