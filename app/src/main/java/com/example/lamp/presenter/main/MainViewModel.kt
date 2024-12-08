package com.example.lamp.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.domain.*
import com.example.lamp.presenter.entity.UIState
import com.example.lamp.presenter.entity.toUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val turnOnUseCase: TurnOnUseCase,
    private val turnOffUseCase: TurnOffUseCase,
    private val changeBrightnessUseCase: ChangeBrightnessUseCase,
    private val getColorsUseCase: GetColorsUseCase,
    private val setColorUseCase: SetColorUseCase,
): ViewModel() {

    private val _stateTurnOn = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val stateTurnOn: LiveData<UIState<Boolean>>
        get() = _stateTurnOn

    private val _stateTurnOff = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val stateTurnOff: LiveData<UIState<Boolean>>
        get() = _stateTurnOff

    private val _stateBrightness = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val stateBrightness: LiveData<UIState<Boolean>>
        get() = _stateBrightness

    private val _colors = MutableLiveData<UIState<List<String>>>(UIState.Loading)
    val colors: LiveData<UIState<List<String>>>
        get() = _colors

    private val _stateColor = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val stateColor: LiveData<UIState<Boolean>>
        get() = _stateColor

    fun turnOn(){
        viewModelScope.launch {
            val result = turnOnUseCase()
            _stateTurnOn.value = result.toUIState()
        }
    }

    fun turnOff(){
        viewModelScope.launch {
            val result = turnOffUseCase()
            _stateTurnOff.value = result.toUIState()
        }
    }

    fun changeBrightness(level: Int){
        viewModelScope.launch {
            val result = changeBrightnessUseCase(level)
            _stateBrightness.value = result.toUIState()
        }
    }

    fun getColors(){
        viewModelScope.launch {
            val result = getColorsUseCase()
            _colors.value = result.toUIState()
        }
    }

    fun setColor(color: String){
        viewModelScope.launch {
            val result = setColorUseCase(color)
            _stateColor.value = result.toUIState()
        }
    }
}