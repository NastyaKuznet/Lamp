package com.example.lamp.di

import com.example.lamp.BuildConfig
import com.example.lamp.data.network.service.LampService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideLampService(): LampService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.LAMP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LampService::class.java)
}