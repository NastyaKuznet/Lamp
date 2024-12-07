package com.example.lamp.domain

import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.repository.LampRepository
import javax.inject.Inject

interface ChangeBrightnessUseCase {

    suspend operator fun invoke(level: Int): StateResponse<Boolean>
}

class ChangeBrightnessUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
): ChangeBrightnessUseCase {

    override suspend fun invoke(level: Int): StateResponse<Boolean> {
        return lampRepository.changeBrightness(level)
    }

}