package com.slutsenko.cocktailapp.presentation

import android.app.Application
import com.slutsenko.cocktailapp.di.Injector

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}