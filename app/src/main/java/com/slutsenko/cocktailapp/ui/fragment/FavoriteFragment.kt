package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_history.*


class FavoriteFragment : BaseFragment<MainViewModel>() {
    override val contentLayoutResId: Int = R.layout.fragment_favorite
    lateinit var cocktailAdapter: CocktailAdapter
    //private lateinit var cocktailList: List<Cocktail>
    override fun onAttach(context: Context) {
        //(context as FilterResultCallback).addCallBack(this)
        super.onAttach(context)
    }

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        viewModel.cocktailDBLiveData?.value = CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
        cocktailAdapter = CocktailAdapter(requireContext(), viewModel.cocktailDBLiveData?.value!!)
        cocktailAdapter.refreshData(viewModel.cocktailDBLiveData?.value!!)
        viewModel.favoriteLiveData.value = viewModel.cocktailDBLiveData?.value!!.filter { it.isFavorite == true }
        //Toast.makeText(requireContext(), "${cocktailList.size}", Toast.LENGTH_LONG).show()


        //Toast.makeText(requireContext(), "${favoriteList.size}", Toast.LENGTH_LONG).show()
        if (viewModel.favoriteLiveData.value!!.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktailAdapter = CocktailAdapter(requireContext(), viewModel.favoriteLiveData.value!!)
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

//    override fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
//        val filteredList: List<Cocktail> = when (alcoholFilter) {
//            AlcoholDrinkFilter.ALCOHOLIC -> favoriteList.filter {
//                it.strAlcoholic == AlcoholDrinkFilter.ALCOHOLIC.key
//            }
//            AlcoholDrinkFilter.NON_ALCOHOLIC -> favoriteList.filter {
//                it.strAlcoholic == AlcoholDrinkFilter.NON_ALCOHOLIC.key
//            }
//            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> favoriteList.filter {
//                it.strAlcoholic == AlcoholDrinkFilter.OPTIONAL_ALCOHOL.key
//            }
//            else -> favoriteList
//        }
//        if (filteredList.isNotEmpty()) {
//            cocktailAdapter.refreshData(filteredList)
//        }
//    }

    override val viewModel: MainViewModel by activityViewModels()

}