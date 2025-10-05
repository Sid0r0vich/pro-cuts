package com.sidor.procuts.network.db

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.ClientInfoDTO
import com.sidor.procuts.data.CutDateDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL = "http://10.0.2.2:1345"

val json = Json { ignoreUnknownKeys = true }
val contentType = "application/json".toMediaType()

val DBRetrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()

interface GinApiService {

    // POST /users
    @POST("/users")
    suspend fun createUser(@Body user: User): User

    // GET /clients?user_id=1
    @GET("/clients")
    suspend fun getClients(@Query("user_id") userId: String): List<ClientDTO>

    // POST /clients
    @POST("/clients")
    suspend fun createClient(@Body client: ClientInfoWithUserIdDTO): ClientDTO

    @PUT("/clients/{id}")
    suspend fun editClient(@Path("id") id: Int, @Body client: ClientInfoDTO): ClientDTO

    // DELETE /clients/{id}
    @DELETE("/clients/{id}")
    suspend fun deleteClient(@Path("id") id: Int): Map<String, String>

    // POST /haircuts
    @POST("/haircuts")
    suspend fun createHaircut(@Body haircut: Haircut): Haircut

    // GET /clients/{id}/haircuts
    @GET("/haircuts")
    suspend fun getHaircuts(@Query("user_id") userId: String): List<CutDateDTO>
}