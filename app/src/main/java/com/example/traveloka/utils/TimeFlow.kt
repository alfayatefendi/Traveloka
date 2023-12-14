package com.example.traveloka.utils

import android.os.CountDownTimer
import android.os.Looper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class TimeFlow private constructor(millisInfuture: Long, countDownInterval: Long){
    private val tick: Flow<Long> = callbackFlow {
        if (Looper.myLooper() == null) {
            throw IllegalStateException("Can't create TimerFlow inside thread that has not called Looper.prepare() Just use Dispatchers.Main")
        }
        object : CountDownTimer(millisInfuture, countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                trySend(millisInfuture).isSuccess
            }
            override fun onFinish() {
                trySend(0L).isSuccess
                cancel()
            }
        }.start()
        awaitClose()
    }
    companion object{
        @JvmStatic
        fun create(millisInfuture: Long, countDownInterval: Long) =
            TimeFlow(millisInfuture, countDownInterval).tick
    }
}