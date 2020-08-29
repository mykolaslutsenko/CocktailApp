package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.FragmentEditProfileBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.SettingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlin.reflect.KClass


class EditProfileFragment : BaseFragment<SettingFragmentViewModel, FragmentEditProfileBinding>() {
    override val viewModelClass: KClass<SettingFragmentViewModel>
        get() = SettingFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_edit_profile


    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        editProfile_btn_save.setOnClickListener {
            viewModel.updateUser()
            parentFragmentManager.popBackStack()
        }
        editProfile_btn_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun configureDataBinding(binding: FragmentEditProfileBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

}