package com.slutsenko.cocktailapp.ui.fragment


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.ui.activity.SearchActivity
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.ui.presentation.adapter.page.FavoritePagerAdapter
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment<MainViewModel>(), CocktailAdapter.OnFavoriteClick {

    override val viewModel: MainViewModel by activityViewModels()

    override val contentLayoutResId: Int = R.layout.fragment_main

    var onFavoriteClick: CocktailAdapter.OnFavoriteClick? = null

    override fun onAttach(context: Context) {
        (context as CocktailAdapter.OnFavoriteClick).refreshDB()
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cocktailDBLiveData?.value =
                CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
        viewModel.setStartParam()
        registerForContextMenu(iv_sort)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.menu_sort_drink, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        viewModel.setSortValue(item)
        return true
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
            val filterFragment = FilterFragment.newInstance()
            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.fcv_main, filterFragment, FilterFragment::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
        }
        iv_main_toolbar_filter.setOnLongClickListener {
            viewModel.dropFilters()
            true
        }
        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

    }

    companion object {
        private var mainFragment: MainFragment? = null
        fun getInstance(): MainFragment {
            if (mainFragment == null) mainFragment = MainFragment()
            return mainFragment as MainFragment
        }

        const val ANOTHER_COCKTAIL = "com.slutsenko.action.anotherCocktail"
        const val COLUMN = 2
    }

    override fun refreshDB() {
        viewModel.cocktailDBLiveData?.value =
                CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
    }


}