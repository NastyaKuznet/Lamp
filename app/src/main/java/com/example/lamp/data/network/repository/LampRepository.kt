package com.example.lamp.data.network.repository

import com.example.lamp.data.network.entity.State
import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.data.network.service.LampService
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

interface LampRepository {

    suspend fun turnOn(): StateResponse<Boolean>
    suspend fun turnOff(): StateResponse<Boolean>
    suspend fun changeBrightness(level: Int): StateResponse<Boolean>
    suspend fun getColors(): StateResponse<List<String>>
    suspend fun setColor(color: String): StateResponse<Boolean>
}

class LampRepositoryImpl @Inject constructor(
    private val lampService: LampService,
): LampRepository {

    override suspend fun turnOn(): StateResponse<Boolean> {
        try {
            val result = lampService.turnOn()
            return if(result.isSuccessful && result.body() != null){
                StateResponse(State.SUCCESS,
                    "Лампочка включена!",
                    result.body()!!)
            } else if(result.code() == 500) {
                StateResponse(State.FAIL,
                    "Сервис лампы не работет",
                    false)
            } else {
                StateResponse(State.FAIL,
                    "Сервис лампы ответил кодом: ${result.code()}",
                    false)
            }
        } catch (e: IOException) {
            return StateResponse(State.FAIL,
                "Ошибка сети: ${e.message}",
                false
            )
        } catch (e: SocketTimeoutException) {
            return StateResponse(State.FAIL,
                "Превышено время ожидания ответа от сервера",
                false)
        } catch (e: Exception) {
            return StateResponse(State.FAIL,
                "Ошибка при включении лампы: ${e.message}",
                false)
        }
    }

    override suspend fun turnOff(): StateResponse<Boolean> {
        try {
            val result = lampService.turnOff()
            return if(result.isSuccessful && result.body() != null){
                StateResponse(State.SUCCESS,
                    "Лампочка выключена!",
                    result.body()!!)
            } else if(result.code() == 500) {
                StateResponse(State.FAIL,
                    "Сервис лампы не работет",
                    false)
            } else {
                StateResponse(State.FAIL,
                    "Сервис лампы ответил кодом: ${result.code()}",
                    false)
            }
        } catch (e: IOException) {
            return StateResponse(State.FAIL,
                "Ошибка сети: ${e.message}",
                false
            )
        } catch (e: SocketTimeoutException) {
            return StateResponse(State.FAIL,
                "Превышено время ожидания ответа от сервера",
                false)
        } catch (e: Exception) {
            return StateResponse(State.FAIL,
                "Ошибка при выключении лампы: ${e.message}",
                false)
        }
    }

    override suspend fun changeBrightness(level: Int): StateResponse<Boolean> {
        try {
            val result = lampService.changeBrightness(level)
            return if(result.isSuccessful && result.body() != null){
                StateResponse(State.SUCCESS,
                    "Яркость лампочки изменена",
                    result.body()!!)
            } else if(result.code() == 500) {
                StateResponse(State.FAIL,
                    "Сервис лампы не работет",
                    false)
            } else if (!result.isSuccessful && result.code()==400){
                StateResponse(State.FAIL,
                    "Лампа выключена",
                    false)
            } else {
                StateResponse(State.FAIL,
                    "Сервис лампы ответил кодом: ${result.code()}",
                    false)
            }
        } catch (e: IOException) {
            return StateResponse(State.FAIL,
                "Ошибка сети: ${e.message}",
                false
            )
        } catch (e: SocketTimeoutException) {
            return StateResponse(State.FAIL,
                "Превышено время ожидания ответа от сервера",
                false)
        } catch (e: Exception) {
            return StateResponse(State.FAIL,
                "Ошибка при изменении яркости лампы: ${e.message}",
                false)
        }
    }

    override suspend fun getColors(): StateResponse<List<String>> {
        try {
            val result = lampService.getColors()
            return if(result.isSuccessful && result.body() != null){
                val r: MutableList<String> = result.body()!!
                r.add(0, "-")
                StateResponse(State.SUCCESS,
                    "Цвета загружены",
                    r)
            } else if(result.code() == 500) {
                StateResponse(State.FAIL,
                    "Сервис лампы не работет",
                    listOf<String>()
                )
            } else {
                StateResponse(State.FAIL,
                    "Сервис лампы ответил кодом: ${result.code()}",
                    listOf<String>())
            }
        } catch (e: IOException) {
            return StateResponse(State.FAIL,
                "Ошибка сети: ${e.message}",
                listOf<String>()
            )
        } catch (e: SocketTimeoutException) {
            return StateResponse(State.FAIL,
                "Превышено время ожидания ответа от сервера",
                listOf<String>())
        } catch (e: Exception) {
            return StateResponse(State.FAIL,
                "Ошибка при получении доступных цветов лампы: ${e.message}",
                listOf<String>())
        }
    }

    override suspend fun setColor(color: String): StateResponse<Boolean> {
        try {
            val result = lampService.setColor(color)
            return if(result.isSuccessful && result.body() != null){
                StateResponse(State.SUCCESS,
                    "Цвет лампочки изменен",
                    result.body()!!)
            } else if(result.code() == 500) {
                StateResponse(State.FAIL,
                    "Сервис лампы не работет",
                    false)
            } else if (!result.isSuccessful && result.code()==400){
                StateResponse(State.FAIL,
                    "Лампа выключена",
                    false)
            } else {
                StateResponse(State.FAIL,
                    "Сервис лампы ответил кодом: ${result.code()}",
                    false)
            }
        } catch (e: IOException) {
            return StateResponse(State.FAIL,
                "Ошибка сети: ${e.message}",
                false
            )
        } catch (e: SocketTimeoutException) {
            return StateResponse(State.FAIL,
                "Превышено время ожидания ответа от сервера",
                false)
        } catch (e: Exception) {
            return StateResponse(State.FAIL,
                "Ошибка при изменении цвета лампы: ${e.message}",
                false)
        }
    }
}