package com.slutsenko.cocktailapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.slutsenko.cocktailapp.BaseActivity
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding

import com.slutsenko.cocktailapp.ui.fragment.MainFragment
import com.slutsenko.cocktailapp.ui.fragment.ProfileFragment
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()


    override fun myView(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainFragment = MainFragment.getInstance()
        val profileFragment = ProfileFragment.getInstance()

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(profileFragment)
                            .show(mainFragment)
                            .commit()
                    true
                }
                R.id.menu_profile -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(mainFragment)
                            .show(profileFragment)
                            .commit()
                    true
                }
                else -> false
            }
        }
        supportFragmentManager.beginTransaction().add(R.id.fcv_main, profileFragment, ProfileFragment::class.java.simpleName).hide(profileFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fcv_main, mainFragment, MainFragment::class.java.simpleName).commit()
    }

    override fun activityCreated() {
        viewModel.showNavigationBarTitlesLiveData.observe(this, Observer<Boolean> {
            if (it) {
                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            } else {
                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
            }
        })
    }
}