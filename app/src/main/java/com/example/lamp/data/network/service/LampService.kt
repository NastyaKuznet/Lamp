package com.example.lamp.data.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LampService {

    @POST("/state/on")
    suspend fun turnOn(): Response<Boolean>

    @POST("/state/off")
    suspend fun turnOff():Response<Boolean>

    @POST("/brightness/")
    suspend fun changeBrightness(@Query("level") level: Int):Response<Boolean>

    @GET("/color/names_only")
    suspend fun getColors():Response<MutableList<String>>

    @POST("/color/")
    suspend fun setColor(@Query("color") color: String): Response<Boolean>
}