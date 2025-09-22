package com.marusys.fitnessapp.feature.account

import android.accounts.Account
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marusys.fitnessapp.model.User
import com.marusys.fitnessapp.repository.AccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val accountRepository: AccountRepository
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
            AccountIntent.ForgotPassword -> {
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
        }
    }

    private fun handleSignUpAccount(user: User) {
        viewModelScope.launch {
            val data =  accountRepository.signUpAccount(user)
            if (data != null) {
                Log.d(TAG, "handleSignUpAccount: =====> VAO 1")
                _accountEvent.tryEmit(AccountEvent.Toast("Create account successfully"))
                delay(300L)
                _accountEvent.tryEmit(AccountEvent.Navigate)

            }else {
                _accountEvent.tryEmit(AccountEvent.Toast("Create account failure"))
            }

        }
    }

    private fun handleSignInAccount(email: String, pass: String): Boolean {

        return false
    }

    fun clearCache(){
        _accountEvent.tryEmit(AccountEvent.None)
    }
}