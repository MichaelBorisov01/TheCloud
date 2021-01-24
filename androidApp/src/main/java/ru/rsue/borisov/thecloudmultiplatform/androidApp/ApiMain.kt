package ru.rsue.borisov.thecloudmultiplatform.androidApp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GraphQLService {

    @Headers("Content-Type: application/json")
    @POST("/registration")
    suspend fun insertReg(@Body body: String): Response<String>

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun getLog(@Body body: String): Response<String>
}