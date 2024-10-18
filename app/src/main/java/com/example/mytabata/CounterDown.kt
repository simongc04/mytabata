package com.example.mytabata

import android.os.CountDownTimer

class CounterDown(var segundos: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var myCounter: CountDownTimer
    var counterState: Boolean = false
    private var remainingTime: Long = (segundos * 1000L)

    init {
        myCounter = crearCountDownTimer()
    }

    fun crearCountDownTimer(): CountDownTimer {
        return object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                loquehacealhacertick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                counterState = false
            }
        }
    }

    fun start() {
        if (!counterState) {
            counterState = true
            myCounter = crearCountDownTimer()
            myCounter.start()
        }
    }

    fun pause() {
        if (counterState) {
            myCounter.cancel()
            counterState = false
        }
    }

    fun cancel() {
        counterState = false
        myCounter.cancel()
    }

    fun reset() {
        remainingTime = (segundos * 1000L)
    }
}