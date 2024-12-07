package com.example.lamp.data.network.entity

data class StateResponse<T>(
    val state: State,
    val message: String,
    val content: T,
)

enum class State{
    SUCCESS,
    FAIL,
}