package com.example.lamp.domain

import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.repository.LampRepository
import javax.inject.Inject

interface GetColorsUseCase {

    suspend operator fun invoke(): StateResponse<List<String>>
}

class GetColorsUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
): GetColorsUseCase {

    override suspend fun invoke(): StateResponse<List<String>> {
        return lampRepository.getColors()
    }

}