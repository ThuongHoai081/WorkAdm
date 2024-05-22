package com.nqv.workadm_app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CallApi {
    private const val BASE_URL = "http://localhost:8080/bo/api/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginController: Controller = retrofit.create(Controller::class.java)
}