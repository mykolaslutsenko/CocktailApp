package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.BaseFragment


class FavoriteFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_favorite

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }

}