package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlin.reflect.KClass


class EditProfileFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_edit_profile

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
    }
}