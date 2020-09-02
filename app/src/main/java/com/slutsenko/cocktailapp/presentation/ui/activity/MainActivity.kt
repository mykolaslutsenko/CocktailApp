package com.slutsenko.cocktailapp.presentation.ui.activity

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.fragment.MainFragment
import com.slutsenko.cocktailapp.presentation.ui.fragment.SettingFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass


class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override val viewModelClass: KClass<MainActivityViewModel>
        get() = MainActivityViewModel::class
    private var mainFragment: MainFragment? = null
    private var settingFragment: SettingFragment? = null

    override fun myView(): Int {
        return R.layout.activity_main
    }


    override fun activityCreated() {

        val config = FirebaseRemoteConfig.getInstance()
        Log.d("config","${config.get("main_toolbar_title").asString()}")
        config.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d("config", "Config params updated: $updated")
                        Toast.makeText(this, "Fetch and activate succeeded",
                                Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Fetch failed",
                                Toast.LENGTH_SHORT).show()
                    }
                    //displayWelcomeMessage()
                }

        var firebase = FirebaseAnalytics.getInstance(this)


        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    firebase.logEvent("main", bundleOf("main" to "click"))
                    supportFragmentManager
                            .beginTransaction()
                            .hide(settingFragment!!)
                            .show(mainFragment!!)
                            .commit()
                    true
                }
                R.id.menu_setting -> {
                    firebase.logEvent("profile", bundleOf("profile" to "click"))
                    supportFragmentManager
                            .beginTransaction()
                            .hide(mainFragment!!)
                            .show(settingFragment!!)
                            .commit()
                    true
                }
                else -> false
            }
        }

        settingFragment = supportFragmentManager.findFragmentByTag(SettingFragment::class.java.name)
                as? SettingFragment
        mainFragment = supportFragmentManager.findFragmentByTag(MainFragment::class.java.name)
                as? MainFragment
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (settingFragment == null) {
            settingFragment = SettingFragment.newInstance()
            fragmentTransaction.add(R.id.fcv_main, settingFragment!!, SettingFragment::class.java.name)
            fragmentTransaction.hide(settingFragment!!)
        }
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance()
            fragmentTransaction.add(R.id.fcv_main, mainFragment!!, MainFragment::class.java.name)
            fragmentTransaction.setPrimaryNavigationFragment(mainFragment!!)
            fragmentTransaction.commit()
        }

        viewModel.showNavigationBarTitlesLiveData.observe(this, Observer<Boolean> {
            if (it) {
                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            } else {
                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
            }
        })
    }
}