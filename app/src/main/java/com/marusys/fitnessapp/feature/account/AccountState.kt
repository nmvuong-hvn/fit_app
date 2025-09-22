package com.marusys.fitnessapp.feature.account

import com.marusys.fitnessapp.model.User

data class AccountState(
    val errorEmail : String = "",
    val errorPassword : String = ""
)

sealed class AccountIntent {
    data object NavigateForgotPassword : AccountIntent()
    data object ClearData : AccountIntent()
    data class SignIn(val email : String , val pass : String) : AccountIntent()

    data class ForgotPassword(val email: String) : AccountIntent()
    data class SignUp(val user: User) : AccountIntent()
    data object Navigate : AccountIntent()
    data object CreateAccount : AccountIntent()
}

sealed class AccountEvent {
    data class ToastMessage<T> (val message : T) : AccountEvent()
    data object None : AccountEvent()
    data object Navigate : AccountEvent()
}