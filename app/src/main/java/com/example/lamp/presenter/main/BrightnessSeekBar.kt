package com.example.lamp.presenter.main

import android.widget.SeekBar

class BrightnessSeekBar(
    private val send: (Int) -> Unit,
): SeekBar.OnSeekBarChangeListener {

    private var state = 0

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        state = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        send(state)
    }
}