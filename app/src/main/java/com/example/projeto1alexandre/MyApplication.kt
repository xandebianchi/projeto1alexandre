package com.example.projeto1alexandre

import android.app.Application
import com.example.projeto1alexandre.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Declare o Android context
            androidContext(this@MyApplication)

            // Modules
            modules(appModule)
        }
    }
}