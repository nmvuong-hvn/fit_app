package com.marusys.fitnessapp.feature.execute_execercise

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class TrainingViewModel : ViewModel() {

    private var startedJob : Job ?= null
    private var timeRemaining = 0L
    private var isPaused = AtomicBoolean(false)
    var timeoutState by mutableStateOf("")

    fun processIntent(intent : TrainingIntent){
        when(intent){
            TrainingIntent.Pause -> {
                handlePauseTimeOut()
            }
            TrainingIntent.TimeoutWorking -> {
                handleTimeoutWorking()
            }

            is TrainingIntent.Initial -> {

            }

            TrainingIntent.Reset -> {
                handleResetTimeOut()
            }
            TrainingIntent.Skip -> {

            }
        }
    }

    private fun handleResetTimeOut() {
        isPaused.set(true)
    }

    private fun handlePauseTimeOut() {
        isPaused.set(false)
    }

    private fun handleTimeoutWorking() {

        startedJob = viewModelScope.launch {
            while (isActive){
                if (isPaused.get()){
                    if (timeRemaining > 0){
                        timeoutState = convertTimeOut(timeRemaining)
                        delay(TimeUnit.SECONDS.toMillis(1))
                    }
                }
                delay(TimeUnit.SECONDS.toMillis(1))
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun convertTimeOut(seconds : Long): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }
}