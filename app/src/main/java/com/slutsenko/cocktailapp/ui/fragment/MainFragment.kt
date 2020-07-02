package com.slutsenko.cocktailapp.ui.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.impl.FilterResultCallback
import com.slutsenko.cocktailapp.ui.activity.SearchActivity
import com.slutsenko.cocktailapp.ui.presentation.adapter.page.FavoritePagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment(), FilterFragment.OnFilterResultListener {

    private var alcoholFilter: AlcoholDrinkFilter? = null

    private var categoryFilter: CategoryDrinkFilter? = null

    override val contentLayoutResId: Int = R.layout.fragment_main

    override fun onAttach(context: Context) {
        (context as FilterResultCallback).addCallBack(this)
        super.onAttach(context)
    }

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)


        viewpager2.adapter = FavoritePagerAdapter(this)
        val tabLayout: TabLayout = requireView().findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            when (position) {
                0 -> tab.text = "History"
                else -> tab.text = "Favorite"
            }
        }.attach()

        iv_main_toolbar_filter.setOnClickListener {
            val filterFragment = FilterFragment.newInstance(alcoholFilter, categoryFilter)
            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.fcv_main, filterFragment, FilterFragment::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
        }
        iv_main_toolbar_filter.setOnLongClickListener {
            alcoholFilter = AlcoholDrinkFilter.NON
            categoryFilter = CategoryDrinkFilter.NON
            iv_indicator.visibility = View.GONE
            true
        }
        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
    }


    companion object {
        lateinit var cocktailList: List<Cocktail>
        private var mainFragment: MainFragment? = null
        fun getInstance(): MainFragment {
            if (mainFragment == null) mainFragment = MainFragment()
            return mainFragment as MainFragment
        }

        const val ANOTHER_COCKTAIL = "com.slutsenko.action.anotherCocktail"
        const val COLUMN = 2
    }

    override fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        this.alcoholFilter = alcoholFilter
        this.categoryFilter = categoryFilter
    }
}