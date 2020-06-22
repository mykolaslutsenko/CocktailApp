package com.slutsenko.cocktailapp.ui.presentation.adapter.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.slutsenko.cocktailapp.ui.fragment.FavoriteFragment
import com.slutsenko.cocktailapp.ui.fragment.MainFragment

class FavoritePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment.newInstance()
            else -> FavoriteFragment.newInstance()
        }
    }
}