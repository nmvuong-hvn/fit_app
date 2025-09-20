package com.marusys.fitnessapp.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.marusys.fitnessapp.R
import kotlinx.collections.immutable.persistentListOf

@Stable
data class Category(
    val id : Int = 0,
    val name : String = "",
    @DrawableRes
    val thumbnail : Int = R.drawable.ic_launcher_background,
    val idsList : String = ""
){
    fun toIdsList() : List<Int>{
        return idsList.split(",").map { it.trim().toInt() }
    }
}

@Stable
data class Exercise(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val videoUrl: String = "",
    val description: String = "",
    val categoryId: Int = 0,
    val isFinished : Boolean = false
)

@Stable
data class ExerciseProgress(
    val executedId: Int = 0,
    val categoryId: Int = 0,
    val exerciseId: Int = 0,
    val timeTotal : Long = 0
)


val typeExerciseList = persistentListOf(
    Category(1, "Full Body Warm Up", R.drawable.exercise_01, ""),
    Category(2, "Strength Exercise", R.drawable.exercise_02, ""),
    Category(3, "Both Side Plank", R.drawable.exercise_03, ""),
    Category(4, "Abs Workout", R.drawable.exercise_04, ""),
    Category(5, "Torso and Trap Workout", R.drawable.exercise_05, ""),
    Category(6, "Lower Back Exercise", R.drawable.exercise_06, "")
)


