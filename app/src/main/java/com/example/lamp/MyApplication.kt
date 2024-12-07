package com.example.lamp

import android.app.Application
import com.example.lamp.di.AppComponent
import com.example.lamp.di.DaggerAppComponent


class MyApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .create()

        super.onCreate()
    }
}