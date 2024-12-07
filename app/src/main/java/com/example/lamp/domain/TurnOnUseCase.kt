package com.example.lamp.domain

import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.repository.LampRepository
import javax.inject.Inject

interface TurnOnUseCase {

    suspend operator fun invoke(): StateResponse<Boolean>
}

class TurnOnUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
): TurnOnUseCase {

    override suspend fun invoke(): StateResponse<Boolean> {
        return lampRepository.turnOn()
    }

}