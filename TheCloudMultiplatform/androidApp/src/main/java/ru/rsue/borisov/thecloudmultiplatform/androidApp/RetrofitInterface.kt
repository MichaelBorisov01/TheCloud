package ru.rsue.borisov.thecloudmultiplatform.androidApp

import com.example.Accounts
import com.example.SignatureContracts
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("all")
    fun getPosts(
        @Query("idSign") userId: MutableList<Int>,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<SignatureContracts>>

    @GET("all")
    fun getPosts(@QueryMap parameters: MutableMap<String, String>): Call<List<SignatureContracts>>

    @POST("all")
    fun createPost(@Body registration : Accounts) : Call<Accounts>
}