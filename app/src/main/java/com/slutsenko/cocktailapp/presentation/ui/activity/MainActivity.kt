package com.slutsenko.cocktailapp.presentation.ui.activity

import androidx.fragment.app.FragmentTransaction
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.fragment.MainFragment
import com.slutsenko.cocktailapp.presentation.ui.fragment.ProfileFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass


class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override val viewModelClass: KClass<MainActivityViewModel>
        get() = MainActivityViewModel::class
    //override val viewModel: MainViewModel by viewModels()
    private var mainFragment: MainFragment? = null
    private var profileFragment: ProfileFragment? = null

    override fun myView(): Int {
        return R.layout.activity_main
    }


    override fun activityCreated() {

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(profileFragment!!)
                            .show(mainFragment!!)
                            .commit()
                    true
                }
                R.id.menu_profile -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(mainFragment!!)
                            .show(profileFragment!!)
                            .commit()
                    true
                }
                else -> false
            }
        }

        profileFragment = supportFragmentManager.findFragmentByTag(ProfileFragment::class.java.simpleName)
                as? ProfileFragment
        mainFragment = supportFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)
                as? MainFragment
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance()
            fragmentTransaction.add(R.id.fcv_main, profileFragment!!, ProfileFragment::class.java.simpleName)
            fragmentTransaction.hide(profileFragment!!)
        }
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance()
            fragmentTransaction.add(R.id.fcv_main, mainFragment!!, MainFragment::class.java.simpleName)
            fragmentTransaction.setPrimaryNavigationFragment(mainFragment!!)
            fragmentTransaction.commit()
        }

//        viewModel.showNavigationBarTitlesLiveData.observe(this, Observer<Boolean> {
//            if (it) {
//                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
//            } else {
//                bottom_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
//            }
//        })
    }


}