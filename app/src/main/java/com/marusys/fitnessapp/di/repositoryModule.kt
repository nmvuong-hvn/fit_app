package com.marusys.fitnessapp.di

import com.marusys.fitnessapp.repository.AccountRepository
import com.marusys.fitnessapp.repository.AccountRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single <AccountRepository>{ AccountRepositoryImpl() }
}