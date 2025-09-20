package com.marusys.fitnessapp.feature.execute_execercise

import com.marusys.fitnessapp.model.Exercise


sealed class TrainingIntent {

    data class Initial(val data : Exercise) : TrainingIntent()
    data object TimeoutWorking : TrainingIntent()
    data object Pause : TrainingIntent()
    data object Reset : TrainingIntent()
    data object Skip : TrainingIntent()
}