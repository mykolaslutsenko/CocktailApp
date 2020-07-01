package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.auth.LoginViewModel
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_profile
    private val viewModel: MainViewModel by activityViewModels()
    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        chb_showTitle.setOnCheckedChangeListener { _, isChecked ->
            viewModel.showNavigationBarTitlesLiveData.value = isChecked
        }

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