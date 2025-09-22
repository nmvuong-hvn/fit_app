package com.marusys.fitnessapp.feature.profile



sealed class ProfileIntent {

    data object NavigateEditProfile : ProfileIntent()
    data object NavigateChangePassword : ProfileIntent()
    data object NavigateHelp : ProfileIntent()
    data object NavigateLanguage: ProfileIntent()
    data object ClearData: ProfileIntent()
    data object Logout: ProfileIntent()

}
sealed class ProfileEvent {
    data object None : ProfileEvent()
    data object NavigateEditProfile : ProfileEvent()
    data object NavigateChangePassword : ProfileEvent()
    data object NavigateHelp : ProfileEvent()
    data object NavigateLanguage: ProfileEvent()
    data object Logout: ProfileEvent()
}