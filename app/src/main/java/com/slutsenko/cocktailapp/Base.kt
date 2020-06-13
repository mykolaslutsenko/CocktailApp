package com.slutsenko.cocktailapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class Base : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myView())
        activityCreated()
    }

    protected abstract fun myView(): Int
    protected abstract fun activityCreated()
}