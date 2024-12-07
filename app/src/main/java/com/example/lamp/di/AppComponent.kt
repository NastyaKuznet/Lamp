package com.example.lamp.di

import com.example.lamp.presenter.main.MainFragment
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        NetworkModule::class,
        AppBindModule::class,
    ]
)
interface AppComponent {
    abstract fun inject(fragment: MainFragment)
}

