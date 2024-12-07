package com.example.lamp.di

import com.example.lamp.data.network.repository.LampRepository
import com.example.lamp.data.network.repository.LampRepositoryImpl
import com.example.lamp.domain.*
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindLampRepository(repository: LampRepositoryImpl): LampRepository

    @Binds
    fun bindTurnOnUseCase(useCase: TurnOnUseCaseImpl): TurnOnUseCase

    @Binds
    fun bindTurnOffUseCase(useCase: TurnOffUseCaseImpl): TurnOffUseCase

    @Binds
    fun bindChangeBrightnessUseCase(useCase: ChangeBrightnessUseCaseImpl): ChangeBrightnessUseCase

    @Binds
    fun bindGetColorsUseCase(useCase: GetColorsUseCaseImpl): GetColorsUseCase

    @Binds
    fun bindSetColorUseCase(useCase: SetColorUseCaseImpl): SetColorUseCase
}