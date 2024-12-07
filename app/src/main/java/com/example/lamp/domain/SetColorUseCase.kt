package com.example.lamp.domain

import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.repository.LampRepository
import javax.inject.Inject

interface SetColorUseCase {
    suspend operator fun invoke(color: String): StateResponse<Boolean>
}

class SetColorUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
): SetColorUseCase {

    override suspend fun invoke(color: String): StateResponse<Boolean> {
        return lampRepository.setColor(color)
    }
}