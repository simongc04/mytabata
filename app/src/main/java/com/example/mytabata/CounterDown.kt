package com.example.mytabata

import android.os.CountDownTimer

class CounterDown(var segundos: Int, var loquehacealhacertick : (Long) -> Unit ) {
    var myCounter : CountDownTimer
    var counterState : Boolean = false


    init {
         myCounter = object : CountDownTimer((segundos * 1000L), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                loquehacealhacertick(millisUntilFinished / 1000)
                //Log.i("dam2", "tick"  + loquehacealhacertick.toString())

            }

            override fun onFinish() {
                counterState = false
            }
        }
    }
}