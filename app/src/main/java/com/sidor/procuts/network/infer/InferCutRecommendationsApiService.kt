package com.sidor.procuts.network.infer

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sidor.procuts.network.googleforms.FormResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL =
    "http://66.151.42.211:8880"

val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()

interface InferCutRecommendationsApiService {
    @POST("infer")
    suspend fun getRecommendations(@Body features: Map<String, String>): PredictionsResponse

    @GET("options")
    suspend fun getOptions(): FormResponse
}

object InferCutRecommendationsApi {
    val retrofitService: InferCutRecommendationsApiService by lazy {
        retrofit.create(InferCutRecommendationsApiService::class.java)
    }
}