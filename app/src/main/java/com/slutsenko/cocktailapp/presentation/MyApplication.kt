package com.slutsenko.cocktailapp.presentation

import android.app.Application
import com.slutsenko.cocktailapp.di.Injector

class MyApplication : Application() {
    override fun onCreate() {
        Injector.init(this)
        super.onCreate()

    }
}