package com.sidor.procuts.network.infer

import kotlinx.serialization.Serializable

@Serializable
data class PredictionsResponse(
    val predictions: List<CutRecommendation>
)

@Serializable
data class CutRecommendation(
    val style: String,
    val prob: Float
)