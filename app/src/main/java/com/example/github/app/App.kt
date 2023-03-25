package com.example.github.app

import android.app.Application
import com.example.github.di.appModule
import com.example.github.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            modules(listOf(appModule, viewModelModule))
        }
    }
}