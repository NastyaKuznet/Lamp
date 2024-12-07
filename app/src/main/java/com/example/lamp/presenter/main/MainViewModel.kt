package com.example.lamp.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val turnOnUseCase: TurnOnUseCase,
    private val turnOffUseCase: TurnOffUseCase,
    private val changeBrightnessUseCase: ChangeBrightnessUseCase,
    private val getColorsUseCase: GetColorsUseCase,
    private val setColorUseCase: SetColorUseCase,
): ViewModel() {

    private val _stateTurnOn = MutableLiveData<StateResponse<Boolean>>()
    val stateTurnOn: LiveData<StateResponse<Boolean>>
        get() = _stateTurnOn

    private val _stateTurnOff = MutableLiveData<StateResponse<Boolean>>()
    val stateTurnOff: LiveData<StateResponse<Boolean>>
        get() = _stateTurnOff

    private val _stateBrightness = MutableLiveData<StateResponse<Boolean>>()
    val stateBrightness: LiveData<StateResponse<Boolean>>
        get() = _stateBrightness

    private val _colors = MutableLiveData<StateResponse<List<String>>>()
    val colors: LiveData<StateResponse<List<String>>>
        get() = _colors

    private val _stateColor = MutableLiveData<StateResponse<Boolean>>()
    val stateColor: LiveData<StateResponse<Boolean>>
        get() = _stateColor

    fun turnOn(){
        viewModelScope.launch {
            val result = turnOnUseCase()
            _stateTurnOn.value = result
        }
    }

    fun turnOff(){
        viewModelScope.launch {
            val result = turnOffUseCase()
            _stateTurnOff.value = result
        }
    }

    fun changeBrightness(level: Int){
        viewModelScope.launch {
            val result = changeBrightnessUseCase(level)
            _stateBrightness.value = result
        }
    }

    fun getColors(){
        viewModelScope.launch {
            val result = getColorsUseCase()
            _colors.value = result
        }
    }

    fun setColor(color: String){
        viewModelScope.launch {
            val result = setColorUseCase(color)
            _stateColor.value = result
        }
    }
}