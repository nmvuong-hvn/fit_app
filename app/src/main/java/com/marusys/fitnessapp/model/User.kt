package com.marusys.fitnessapp.model

import android.util.Log
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.encoders.annotations.Encodable

enum class ValidAccount(val data: String){
    Not_Length("The password is more 6 character"),
    Not_Matching("The password is not matching"),

    Matching("")
}
data class User(
    val idUser : String = "",
    val fullName : String = "",
    val email : String = "",
    val phone : String = "",
    val address : String = ""
){
    private val TAG = "test"
    private var password : String = ""
    private var confirmPassword : String = ""

    fun setPassword(data: String) {
        this.password = data
    }
    fun setConfirmPassword(data: String){
        this.confirmPassword = data
    }

    @Encodable.Ignore
    fun getPassword() = this.password

    @Encodable.Ignore
    fun isValidPassword(): ValidAccount {
        return if (password.length <= 6) {
            ValidAccount.Not_Length
        }else if (password != confirmPassword) {
            ValidAccount.Not_Matching
        }else{
            ValidAccount.Matching
        }
    }
}
