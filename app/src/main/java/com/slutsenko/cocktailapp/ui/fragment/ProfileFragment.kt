package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.db.CocktailDatabase

class ProfileFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_profile
    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)


    }

    companion object {

        fun newInstance() = ProfileFragment()

    }
}