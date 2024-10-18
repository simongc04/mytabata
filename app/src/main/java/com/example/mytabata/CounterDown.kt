package com.example.mytabata

import android.os.CountDownTimer

class CounterDown(var segundos: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var myCounter: CountDownTimer
    var counterState: Boolean = false
    var tiempoRestante: Long = (segundos * 1000L)

    init {
        myCounter = crearCountDownTimer()
    }

    fun crearCountDownTimer(): CountDownTimer {
        return object : CountDownTimer(tiempoRestante, 1000) {
            // Se llama cada segundo mientras el temporizador está activo.
            override fun onTick(millisUntilFinished: Long) {
                // Actualiza el tiempo restante.
                tiempoRestante = millisUntilFinished
                // Llama a la función para hacer algo con el tiempo restante en segundos.
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
        tiempoRestante = (segundos * 1000L)
    }
}