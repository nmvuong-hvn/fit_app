package com.marusys.fitnessapp.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.marusys.fitnessapp.R

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
