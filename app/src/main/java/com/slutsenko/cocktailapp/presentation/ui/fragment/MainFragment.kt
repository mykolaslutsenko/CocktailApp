package com.slutsenko.cocktailapp.presentation.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.FragmentMainBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.FilterAdapter
import com.slutsenko.cocktailapp.presentation.adapter.page.FavoritePagerAdapter
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailAlcoholType
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailCategory
import com.slutsenko.cocktailapp.presentation.model.cocktail.DrinkFilter
import com.slutsenko.cocktailapp.presentation.model.cocktail.SortDrink
import com.slutsenko.cocktailapp.presentation.ui.activity.SearchActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.reflect.KClass


class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class

    override val contentLayoutResId: Int = R.layout.fragment_main

    lateinit var filterAdapter: FilterAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        remoteConfigToolbar()

        viewModel.historyLiveData.observe(requireActivity(), Observer {
            val list = mutableListOf<DrinkFilter>()
            list.add(viewModel.alcoholDrinkFilterLiveData.value as DrinkFilter)
            list.add(viewModel.categoryDrinkFilterLiveData.value as DrinkFilter)
            filterAdapter = FilterAdapter(list, requireActivity(), viewModel)
            rv_filter.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rv_filter.adapter = filterAdapter

            if (viewModel.sortDrinkLiveData.value != SortDrink.RECENT) {
                iv_sort_indicator.visibility = View.VISIBLE
            } else {
                iv_sort_indicator.visibility = View.GONE
            }
            if (viewModel.alcoholDrinkFilterLiveData.value != CocktailAlcoholType.UNDEFINED ||
                    viewModel.categoryDrinkFilterLiveData.value != CocktailCategory.UNDEFINED) {
                iv_indicator_filter.visibility = View.VISIBLE
            } else {
                iv_indicator_filter.visibility = View.GONE
            }
        })


        viewpager2.adapter = FavoritePagerAdapter(this)
        val tabLayout: TabLayout = requireView().findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.history)
                else -> tab.text = getString(R.string.favorite)
            }
        }.attach()

        btn_filter.setOnClickListener {
            val filterFragment = FilterFragment.newInstance()
            childFragmentManager.beginTransaction()
                    .add(R.id.fcv_main_fragment, filterFragment, FilterFragment::class.java.name)
                    .addToBackStack(FilterFragment::class.java.name)
                    .commit()
        }
        btn_filter.setOnLongClickListener {
            viewModel.dropFilters()
            iv_indicator_filter.visibility = View.GONE
            true
        }
        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        btn_sort.setOnClickListener { v: View ->
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
        btn_sort.setOnLongClickListener {
            viewModel.dropSort()
            true
        }
    }

    private fun remoteConfigToolbar() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1
        }
        val config = FirebaseRemoteConfig.getInstance()
        config.setConfigSettingsAsync(configSettings)

        Log.d("config", config.get("main_toolbar_title").asString())
        val toolbar = config.get("main_toolbar_title").asString()
        config.fetchAndActivate()
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        title_toolbar.text = toolbar
                        val updated = task.result
                        Log.d("config", "Config params updated: $updated")
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