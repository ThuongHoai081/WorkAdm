package com.nqv.workadm_app.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Controller {
    @POST("login")
    suspend fun login(@Body request: Any): Response<Any>
}