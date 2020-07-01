package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.R

class ProfileFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_profile
    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)


    }

    companion object {
        private var profileFragment: ProfileFragment? = null
        fun getInstance(): ProfileFragment {
            if (profileFragment == null) {
                profileFragment = ProfileFragment()
            }
            return profileFragment as ProfileFragment
        }

    }
}