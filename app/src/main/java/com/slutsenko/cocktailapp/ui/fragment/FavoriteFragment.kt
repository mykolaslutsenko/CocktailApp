package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.ui.fragment.MainFragment.Companion.cocktailList
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import kotlinx.android.synthetic.main.fragment_history.*


class FavoriteFragment : BaseFragment() {
    var alcoholDrinkFilter:AlcoholDrinkFilter? = null
    override val contentLayoutResId: Int = R.layout.fragment_favorite

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        cocktailList = MainFragment.cocktailDatabase?.cocktailDao()?.cocktails  as List <Cocktail>
        if (cocktailList.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            //cocktailList.reverse()
            filterAlcohol(cocktailList, alcoholDrinkFilter)
            val cocktailAdapter = CocktailAdapter(requireContext(), cocktailList)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
    }

    private fun filterAlcohol(cocktailList: List<Cocktail>, filter: AlcoholDrinkFilter?): List<Cocktail> {
        return when (filter) {
            AlcoholDrinkFilter.ALCOHOLIC -> cocktailList.filter { it.strAlcoholic == AlcoholDrinkFilter.ALCOHOLIC.key }
            AlcoholDrinkFilter.NON_ALCOHOLIC -> cocktailList.filter { it.strAlcoholic == AlcoholDrinkFilter.NON_ALCOHOLIC.key }
            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> cocktailList.filter { it.strAlcoholic == AlcoholDrinkFilter.OPTIONAL_ALCOHOL.key }
            else -> cocktailList
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }

}