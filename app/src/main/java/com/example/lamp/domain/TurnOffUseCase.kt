package com.example.lamp.domain

import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.repository.LampRepository
import javax.inject.Inject

interface TurnOffUseCase {

    suspend operator fun invoke(): StateResponse<Boolean>
}

class TurnOffUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
): TurnOffUseCase {

    override suspend fun invoke(): StateResponse<Boolean> {
        return lampRepository.turnOff()
    }
}