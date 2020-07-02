package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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


class FavoriteFragment : BaseFragment(), FilterFragment.OnFilterResultListener {
    override val contentLayoutResId: Int = R.layout.fragment_favorite
    lateinit var cocktailAdapter: CocktailAdapter
    private lateinit var favoriteList:List<Cocktail>
    override fun onAttach(context: Context) {
        (context as FilterResultCallback).addCallBack(this)
        super.onAttach(context)
    }

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        cocktailList = CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
        favoriteList = cocktailList.filter { it.isFavorite == true }
        //Toast.makeText(requireContext(), "${cocktailList.size}", Toast.LENGTH_LONG).show()
        cocktailAdapter = CocktailAdapter(requireContext(), favoriteList)
        cocktailAdapter.refreshData(favoriteList)
        //Toast.makeText(requireContext(), "${favoriteList.size}", Toast.LENGTH_LONG).show()
        if (favoriteList.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktailAdapter = CocktailAdapter(requireContext(), favoriteList)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
    }

    companion object {
        private var favoriteFragment: FavoriteFragment? = null
        fun getInstance(): FavoriteFragment {
            if (favoriteFragment == null) favoriteFragment = FavoriteFragment()
            return favoriteFragment as FavoriteFragment
        }
    }

    override fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        val filteredList: List<Cocktail> = when (alcoholFilter) {
            AlcoholDrinkFilter.ALCOHOLIC -> favoriteList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.ALCOHOLIC.key
            }
            AlcoholDrinkFilter.NON_ALCOHOLIC -> favoriteList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.NON_ALCOHOLIC.key
            }
            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> favoriteList.filter {
                it.strAlcoholic == AlcoholDrinkFilter.OPTIONAL_ALCOHOL.key
            }
            else -> favoriteList
        }
        if (filteredList.isNotEmpty()) {
            cocktailAdapter.refreshData(filteredList)
        }
    }
}