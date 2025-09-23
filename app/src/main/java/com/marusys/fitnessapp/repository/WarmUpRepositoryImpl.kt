package com.marusys.fitnessapp.repository

import android.util.SparseArray
import androidx.compose.ui.util.fastForEachIndexed
import com.google.mediapipe.formats.proto.LandmarkProto

enum class WarmUpExercise(val exerciseName: String) {
    JUMPING_JACK("Jumping Jack"),
    LUNGES("Lunges"),
    SQUAT("Squat"),
    HIGH_KNEE("High Knee"),
    LEG_SWINGS("Leg Swings"),
}
class WarmUpRepositoryImpl : WorkCountRepository {
    override val name: String get() = "WarmUp"
    val cachedExerciseList = SparseArray<WarmUpExercise>()
    override suspend fun setExerciseList(exerciseList: List<WarmUpExercise>) {
        exerciseList.fastForEachIndexed { i, value ->
            cachedExerciseList.put(i, value)
        }
    }

    override suspend fun doProcess(index : Int , dataList: List<LandmarkProto.NormalizedLandmark>): WorkCountResult {
        return WorkCountResult(
            count = 0,
            message = "WarmUp not count"
        )
    }
}