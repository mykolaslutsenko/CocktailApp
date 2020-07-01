package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.impl.FilterResultCallback
import com.slutsenko.cocktailapp.ui.fragment.MainFragment.Companion.cocktailList
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment(), FilterFragment.OnFilterResultListener {
    override val contentLayoutResId: Int = R.layout.fragment_history
    lateinit var cocktailAdapter: CocktailAdapter
    lateinit var historyList: List<Cocktail>

    override fun onAttach(context: Context) {
        (context as FilterResultCallback).addCallBack(this)
        super.onAttach(context)
    }

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        cocktailList = CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
        historyList = cocktailList

        if (historyList.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktailAdapter = CocktailAdapter(requireContext(), historyList)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
    }

    companion object {
        private var historyFragment: HistoryFragment? = null
        fun getInstance(): HistoryFragment {
            if (historyFragment == null) {
                historyFragment = HistoryFragment()
            }
            return historyFragment as HistoryFragment
        }
    }

    override fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        val alcoholFilteredList: List<Cocktail> = when (alcoholFilter) {
            AlcoholDrinkFilter.ALCOHOLIC -> historyList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.ALCOHOLIC.key
            }
            AlcoholDrinkFilter.NON_ALCOHOLIC -> historyList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.NON_ALCOHOLIC.key
            }
            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> historyList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.OPTIONAL_ALCOHOL.key
            }
            AlcoholDrinkFilter.NON -> cocktailList
            else -> cocktailList
        }

        val categoryFilteredList = when (categoryFilter) {
            CategoryDrinkFilter.ORDINARY_DRINK -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.ORDINARY_DRINK.key
            }
            CategoryDrinkFilter.COCKTAIL -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.COCKTAIL.key
            }
            CategoryDrinkFilter.MILK_FLOAT_SHAKE -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.MILK_FLOAT_SHAKE.key
            }
            CategoryDrinkFilter.OTHER_UNKNOWN -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.OTHER_UNKNOWN.key
            }
            CategoryDrinkFilter.COCOA -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.COCOA.key
            }
            CategoryDrinkFilter.SHOT -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.SHOT.key
            }
            CategoryDrinkFilter.COFFEE_TEA -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.COFFEE_TEA.key
            }
            CategoryDrinkFilter.HOMEMADE_LIQUEUR -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.HOMEMADE_LIQUEUR.key
            }
            CategoryDrinkFilter.PUNCH_PARTY_DRINK -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.PUNCH_PARTY_DRINK.key
            }
            CategoryDrinkFilter.BEER -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.BEER.key
            }
            CategoryDrinkFilter.SOFT_DRINK_SODA -> alcoholFilteredList.filter {
                it.strCategory == CategoryDrinkFilter.SOFT_DRINK_SODA.key
            }
            CategoryDrinkFilter.NON -> alcoholFilteredList
            else -> alcoholFilteredList
        }
        cocktailAdapter.refreshData(categoryFilteredList)
    }
}