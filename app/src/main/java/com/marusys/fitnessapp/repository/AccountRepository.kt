package com.marusys.fitnessapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.marusys.fitnessapp.model.User

interface AccountRepository {


    suspend fun signInAccount(email : String, pass : String) : User?
    suspend fun signUpAccount(user : User): FirebaseUser?
}