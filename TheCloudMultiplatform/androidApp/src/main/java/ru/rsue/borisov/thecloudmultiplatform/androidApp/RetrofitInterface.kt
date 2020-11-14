package ru.rsue.borisov.thecloudmultiplatform.androidApp

import com.example.SignatureContracts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitInterface {
    @GET("all")
    fun getPosts(
        @Query("idSign") userId: MutableList<Int>,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<SignatureContracts>>

    @GET("all")
    fun getPosts(@QueryMap parameters: MutableMap<String, String>): Call<List<SignatureContracts>>
}