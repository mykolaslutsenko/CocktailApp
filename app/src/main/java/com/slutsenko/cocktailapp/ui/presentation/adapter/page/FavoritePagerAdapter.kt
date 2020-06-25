package com.slutsenko.cocktailapp.ui.presentation.adapter.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.slutsenko.cocktailapp.ui.fragment.FavoriteFragment
import com.slutsenko.cocktailapp.ui.fragment.HistoryFragment
import com.slutsenko.cocktailapp.ui.fragment.MainFragment

class FavoritePagerAdapter(fr: Fragment) : FragmentStateAdapter(fr) {
    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HistoryFragment.newInstance()
            else -> FavoriteFragment.newInstance()
        }
    }
}