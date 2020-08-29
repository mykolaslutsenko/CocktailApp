package com.slutsenko.cocktailapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.auth.LoginActivity
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.viewmodel.MainActivityViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.SplashActivityViewModel
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<SplashActivityViewModel, ActivityMainBinding>() {

    override val viewModelClass: KClass<SplashActivityViewModel>
        get() = SplashActivityViewModel::class

    override fun myView(): Int {
        return R.layout.activity_splash
    }

    override fun activityCreated() {
        //stub
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.tokenLiveData.value.isNullOrEmpty()) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}