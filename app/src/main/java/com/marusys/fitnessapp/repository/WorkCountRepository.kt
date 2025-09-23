package com.marusys.fitnessapp.repository

import com.google.mediapipe.formats.proto.LandmarkProto

interface WorkCountRepository {
    val name : String
    suspend fun setExerciseList(exerciseList: List<WarmUpExercise>)
    suspend fun doProcess(index : Int ,dataList: List<LandmarkProto.NormalizedLandmark>): WorkCountResult

}

data class WorkCountResult(
    val count : Int,
    val idEx : Int = 0,
    val message : String
)