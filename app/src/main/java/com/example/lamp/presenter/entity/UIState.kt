package com.example.lamp.presenter.entity

import com.example.lamp.data.network.entity.State
import com.example.lamp.data.network.entity.StateResponse

sealed class UIState<out T> {
    class Success<T>(val value: T, val message: String): UIState<T>()
    class Fail(val message: String): UIState<Nothing>()
    data object Loading: UIState<Nothing>()
}

fun <T> StateResponse<T>.toUIState(): UIState<T>{
    return if(this.state == State.SUCCESS){
        UIState.Success<T>(this.content, this.message)
    } else{
        UIState.Fail(this.message)
    }
}