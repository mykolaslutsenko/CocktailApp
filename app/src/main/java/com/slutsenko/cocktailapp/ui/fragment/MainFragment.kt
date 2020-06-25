package com.slutsenko.cocktailapp.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.ui.SearchActivity
import com.slutsenko.cocktailapp.ui.presentation.adapter.page.FavoritePagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment() {


    private var alcoholFilter: AlcoholDrinkFilter? = null


    private var categoryDrinkFilter: CategoryDrinkFilter? = null

    override val contentLayoutResId: Int = R.layout.fragment_main

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

        iv_main_toolbar_filter.setOnClickListener{
            val filterFragment = FilterFragment.newInstance(alcoholFilter, categoryDrinkFilter)
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fcv_main, filterFragment, FilterFragment::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
        }
        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        cocktailDatabase = Room.databaseBuilder(requireContext(),
                CocktailDatabase::class.java, "cocktail5").allowMainThreadQueries().build()

    }



    companion object {
        lateinit var cocktailList: List<Cocktail>
        fun newInstance() = MainFragment()
        const val ANOTHER_COCKTAIL = "com.slutsenko.action.anotherCocktail"
        const val COLUMN = 2
        var cocktailDatabase: CocktailDatabase? = null
    }
}