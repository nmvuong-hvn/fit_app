package com.marusys.fitnessapp.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.marusys.fitnessapp.model.User
import com.marusys.fitnessapp.model.ValidAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class AccountRepositoryImpl : AccountRepository {
    private val TAG = "AccountRepositoryImpl"
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val database : DatabaseReference = FirebaseDatabase.getInstance().reference
    override suspend fun signInAccount(email: String, pass: String) = withContext(Dispatchers.IO) {
        val data = runCatching {
            val taskResult =  firebaseAuth.signInWithEmailAndPassword(email, pass).await()
           if (taskResult != null && taskResult.user != null){
               val dataRes = database.child("User").child(taskResult.user!!.uid).get().await()
               val user = dataRes.getValue(User::class.java)
               user
           }else {
               null
           }
        }.onFailure {
            Log.d(TAG, "signInAccount: ====> ${it.message}")
            null
        }
        return@withContext data.getOrNull()
    }

    override suspend fun signUpAccount(user: User) = withContext(Dispatchers.IO) {
        val data = runCatching {
            val isValid = user.isValidPassword()
            if (isValid == ValidAccount.Matching) {

                val dataUser = firebaseAuth.createUserWithEmailAndPassword(user.email, user.getPassword()).await()
                if (dataUser != null && dataUser.user != null) {
                    val cloneUser = user.copy(idUser = dataUser.user!!.uid)
                    database.child("User").child(dataUser.user!!.uid).setValue(cloneUser)
                }
                dataUser
            } else {
                Log.d(TAG, "signUpAccount: valid = ${isValid.data}")
                null
            }
        }.onFailure {
            Log.d(TAG, "signUpAccount: ====> ${it.message}")
            null
        }
        return@withContext data.getOrNull()?.user
    }

}