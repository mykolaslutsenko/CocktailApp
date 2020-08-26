package com.slutsenko.cocktailapp.presentation.ui.activity

import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.LabelVisibilityMode
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
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(settingFragment!!)
                            .show(mainFragment!!)
                            .commit()
                    true
                }
                R.id.menu_setting -> {
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