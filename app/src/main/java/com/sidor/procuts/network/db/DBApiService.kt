package com.sidor.procuts.network.db

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.ClientInfoDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.data.models.UserDTO
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

val BASE_URL = "http://66.151.42.211:1345"

val json = Json { ignoreUnknownKeys = true }
val contentType = "application/json".toMediaType()

val DBRetrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()

interface GinApiService {

    // GET /users?user_id={userId}
    @GET("/users")
    suspend fun getUser(@Query("user_id") userId: String): UserDTO

    // POST /users
    @POST("/users")
    suspend fun createUser(@Body user: UserDTO): UserDTO

    // PUT /users
    @PUT("/users")
    suspend fun updateUser(@Body user: UserDTO): UserDTO

    // POST /users
    @DELETE("/users")
    suspend fun deleteUser(@Query("user_id") userId: String): UserDTO

    // GET /clients?user_id={userId}
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

    // GET /clients/{id}/haircuts
    @GET("/haircuts")
    suspend fun getHaircuts(@Query("user_id") userId: String): List<CutDateDTO>

    // POST /haircuts
    @POST("/haircuts")
    suspend fun createHaircut(@Body haircut: CutDateInfoDTO): CutDateDTO

    // DELETE /haircuts/{id}
    @DELETE("/haircuts/{id}")
    suspend fun deleteCutDate(@Path("id") id: Int): Map<String, String>
}