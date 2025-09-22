package com.marusys.fitnessapp.feature.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ProfileViewModel : ViewModel() {

    private val _eventSetting = MutableSharedFlow<ProfileEvent>(replay = 1)
    val eventSetting = _eventSetting.asSharedFlow()


    fun processIntent(intent: ProfileIntent){
        when(intent){
            ProfileIntent.Logout -> {
            }
            ProfileIntent.NavigateChangePassword -> {
                _eventSetting.tryEmit(ProfileEvent.NavigateChangePassword)
            }
            ProfileIntent.NavigateEditProfile -> {
                _eventSetting.tryEmit(ProfileEvent.NavigateEditProfile)
            }
            ProfileIntent.NavigateHelp -> {
                _eventSetting.tryEmit(ProfileEvent.NavigateHelp)
            }
            ProfileIntent.NavigateLanguage -> {
                _eventSetting.tryEmit(ProfileEvent.NavigateLanguage)

            }

            ProfileIntent.ClearData -> {
                _eventSetting.tryEmit(ProfileEvent.None)
            }
        }
    }

}

