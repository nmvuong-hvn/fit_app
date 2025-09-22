package com.marusys.fitnessapp.feature.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.model.User
import com.marusys.fitnessapp.repository.AccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val accountRepository: AccountRepository,
    private val applicationContext: Context
) : ViewModel() {
    private val TAG = "AccountViewModel"
    private val _accountState = MutableStateFlow(AccountState())
    val accountState = _accountState.asStateFlow()

    private val _accountEvent = MutableSharedFlow<AccountEvent>(replay = 1)
    val accountEvent = _accountEvent.asSharedFlow()

    fun processIntent(accountIntent: AccountIntent){
        when(accountIntent){
            AccountIntent.CreateAccount -> {
                _accountEvent.tryEmit(AccountEvent.Navigate)
            }
            AccountIntent.NavigateForgotPassword -> {
                _accountEvent.tryEmit(AccountEvent.Navigate)
            }
            is AccountIntent.SignIn -> {
                handleSignInAccount(accountIntent.email, accountIntent.pass)
            }

            AccountIntent.Navigate -> {
                clearCache()
            }

            is AccountIntent.SignUp -> {
                handleSignUpAccount(accountIntent.user)
            }

            is AccountIntent.ForgotPassword -> {
                handleSendMailForgotPassword(accountIntent.email)
            }

            AccountIntent.ClearData -> {
                clearCache()
            }
        }
    }

    private fun handleSendMailForgotPassword(email: String) {
        viewModelScope.launch {
            val dataRes = accountRepository.sendMail(email)
            if (dataRes.isSuccess == true ){
                _accountEvent.tryEmit(AccountEvent.ToastMessage( applicationContext.getString(R.string.send_mail_success, email)))
            }else {
                _accountEvent.tryEmit(AccountEvent.ToastMessage(applicationContext.getString(R.string.send_mail_failure, email)))
            }
        }
    }

    private fun handleSignUpAccount(user: User) {
        viewModelScope.launch {
            val data =  accountRepository.signUpAccount(user)
            if (data != null) {
                Log.d(TAG, "handleSignUpAccount: =====> VAO 1")
                _accountEvent.tryEmit(AccountEvent.ToastMessage(AccountRepoState(true,applicationContext.getString(R.string.create_account_success))))
            }else {
                _accountEvent.tryEmit(AccountEvent.ToastMessage(AccountRepoState(false,applicationContext.getString(R.string.create_account_failure))))
            }

        }
    }

    private fun handleSignInAccount(email: String, pass: String) {
        viewModelScope.launch {
           val user =  accountRepository.signInAccount(email, pass)
            Log.d(TAG, "handleSignInAccount: ====> user = $user")
            if (user == null) return@launch
            if (user.isSuccess != null){
                _accountEvent.tryEmit(AccountEvent.ToastMessage(user.copy(message = applicationContext.getString(R.string.login_success))))
            }else {
                _accountEvent.tryEmit(AccountEvent.ToastMessage(user.copy(message = applicationContext.getString(R.string.login_failure))))
            }
        }
    }

    fun clearCache(){
        _accountEvent.tryEmit(AccountEvent.None)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ======> ")
    }
}

data class AccountRepoState<out T>(
    val isSuccess : T ?= null ,
    val message : String = ""
)