package com.marusys.fitnessapp.di

import android.accounts.Account
import com.marusys.fitnessapp.feature.account.AccountViewModel
import com.marusys.fitnessapp.feature.profile.ProfileViewModel
import com.marusys.fitnessapp.repository.AccountRepository
import com.marusys.fitnessapp.repository.AccountRepositoryImpl
import org.koin.dsl.module

val viewModelModule = module {
    single <AccountViewModel>{ AccountViewModel(get(),get()) }
    single <ProfileViewModel>{ ProfileViewModel() }
}