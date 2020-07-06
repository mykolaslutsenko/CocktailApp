package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<MainViewModel>() {
    override val contentLayoutResId: Int = R.layout.fragment_profile
    override val viewModel: MainViewModel by activityViewModels()
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