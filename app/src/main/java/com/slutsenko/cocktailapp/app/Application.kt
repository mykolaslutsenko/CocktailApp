package com.slutsenko.cocktailapp.app

import android.app.Application
import java.util.*

class Application : Application() {
    override fun onCreate() {
        Locale.setDefault(Locale.UK)
        super.onCreate()

    }
}