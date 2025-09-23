package com.marusys.fitnessapp

import android.app.Application
import com.google.mediapipe.formats.proto.LandmarkProto
import com.marusys.fitnessapp.di.appModule
import com.marusys.fitnessapp.di.repositoryModule
import com.marusys.fitnessapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FitApp  : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FitApp)
            modules(appModule, repositoryModule, viewModelModule)
        }
    }
}