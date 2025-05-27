package com.sidor.procuts.network.googleforms

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://forms.googleapis.com/v1/forms/"
val token = "ya29.a0AW4XtxhJzY8-wwnmA78SypIvQtgXLxNFI0ixecq8hhonMDbK_HDXepzrvcHHWGC8GBk_c5K4cqCh1S96NZnQCNXce3UbnppYF1_1X9hHgXvK7dYfytc-4hzUMrlDgqzz6wpz2oqiVPAdFQhIJd-33ftdM75dkzsBlXzLoSXpaCgYKAc0SARUSFQHGX2MiscPA7INW_GFE1FBJwv_RJg0175"

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor(token))
    .build()

val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    )
    .build()

interface GoogleFormsApiService {
    @GET("{formId}")
    suspend fun getForm(@Path("formId") formId: String): FormResponse
}

object GoogleFormsApi {
    val retrofitService: GoogleFormsApiService by lazy {
        retrofit.create(GoogleFormsApiService::class.java)
    }
}