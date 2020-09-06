package com.slutsenko.cocktailapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.auth.LoginActivity
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.fragment.ProfileFragment
import com.slutsenko.cocktailapp.presentation.ui.fragment.SettingFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.SplashActivityViewModel
import com.slutsenko.cocktailapp.service.MessagingModel
import com.slutsenko.cocktailapp.service.MessagingType
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
            val messagingModel: MessagingModel? = intent.getSerializableExtra("message") as? MessagingModel
            when (messagingModel?.type) {
                MessagingType.MAIN -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
                MessagingType.COCKTAIL_DETAIL -> {
                    starAboutCocktailActivity(messagingModel.cocktailId!!)
                }

                MessagingType.PROFILE -> {
                    startProfileFragment()
                }
                MessagingType.PROFILE_EDIT -> {
                    startEditProfileFragment()
                }
                MessagingType.RATE_APP -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    showRateAppDialog()
                }
                else -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }

            }

            finish()
        }
    }

    private fun starAboutCocktailActivity(id: Long) {
        intent = Intent(this, AboutCocktailActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra("cocktail_id_notification", id)
        }
        startActivity(intent)
    }

    private fun showRateAppDialog() {

    }

    private fun startEditProfileFragment() {

    }

    private fun startProfileFragment() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))


        val settingFragment = SettingFragment()
        supportFragmentManager.beginTransaction().add(R.id.fcv_main, settingFragment, SettingFragment::class.java.name).commit()

        val profileFragment = ProfileFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fcv_setting_fragment, profileFragment, ProfileFragment::class.java.name)
                .addToBackStack(ProfileFragment::class.java.name)
                .commit()
    }
}