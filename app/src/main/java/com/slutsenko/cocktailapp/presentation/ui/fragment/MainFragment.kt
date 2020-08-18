package com.slutsenko.cocktailapp.presentation.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.adapter.page.FavoritePagerAdapter
import com.slutsenko.cocktailapp.presentation.ui.activity.SearchActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.reflect.KClass


class MainFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class

    override val contentLayoutResId: Int = R.layout.fragment_main

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        viewpager2.adapter = FavoritePagerAdapter(this)
        val tabLayout: TabLayout = requireView().findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.history)
                else -> tab.text = getString(R.string.favorite)
            }
        }.attach()

        iv_main_toolbar_filter.setOnClickListener {
            val filterFragment = FilterFragment.newInstance()
            childFragmentManager.beginTransaction()
                    .add(R.id.fcv_main_fragment, filterFragment, FilterFragment::class.java.simpleName)
                    .addToBackStack(FilterFragment::class.java.name)
                    .commit()
        }
        iv_main_toolbar_filter.setOnLongClickListener {
            viewModel.dropFilters()
            true
        }
        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        iv_sort.setOnClickListener { v: View ->
            PopupMenu(requireContext(), v).apply {

                setOnMenuItemClickListener {
                    viewModel.setSortValue(it)
                    viewModel.sortIdLiveData.value = it.itemId
                    true
                }

                inflate(R.menu.menu_sort_drink)
                menu.findItem(viewModel.sortIdLiveData.value!!).isEnabled = false
                show()
            }
        }
    }


    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }

        const val ANOTHER_COCKTAIL = "com.slutsenko.action.anotherCocktail"
        const val COLUMN = 2
    }
}