package com.sidor.procuts.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL =
    "http://192.168.0.104:9000"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()

interface InferCutRecommendationsApiService {
    @POST("infer")
    suspend fun getRecommendations(@Body features: Features): List<CutRecommendation>
}

object InferCutRecommendationsApi {
    val retrofitService: InferCutRecommendationsApiService by lazy {
        retrofit.create(InferCutRecommendationsApiService::class.java)
    }
}