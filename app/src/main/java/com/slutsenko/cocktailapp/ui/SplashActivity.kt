package com.slutsenko.cocktailapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.slutsenko.cocktailapp.auth.LoginActivity
import com.slutsenko.cocktailapp.auth.LoginViewModel

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }
}