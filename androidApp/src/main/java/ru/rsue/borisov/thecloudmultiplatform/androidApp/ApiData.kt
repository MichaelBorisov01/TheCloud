package ru.rsue.borisov.thecloudmultiplatform.androidApp

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object GraphQLInstance {
    private const val BASE_URL: String = "http://192.168.0.104:8080"
    const val token: String =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6Imh2MHJvc3QiLCJlbWFpbCI6ImFydHNlcm92MjAwMUBnbWFpbC5jb20ifQ.OJ0-U3M5OMJrcb171ca_TcN79qB-UqGbk4Kerac_xvbhT-ZAxJIyN1ju1VD0JMTlrDnO8stkilsS65bhVrG0vA"
    private var client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }.build()
    val graphQLService: GraphQLService by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(GraphQLService::class.java)
    }
}