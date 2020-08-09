package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.reflect.KClass


class FavoriteFragment : BaseFragment<MainFragmentViewModel>(), CocktailAdapter.OnFavoriteClick {

    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
   // private val viewModel: MainViewModel by activityViewModels()
    override val contentLayoutResId: Int = R.layout.fragment_favorite
    lateinit var cocktailAdapter: CocktailAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        viewModel.favoriteLiveData.value = viewModel.cocktailDBLiveData?.value!!.filter { it.isFavorite == true }
        cocktailAdapter = CocktailAdapter(requireContext(), viewModel.favoriteLiveData.value!!)

        if (viewModel.favoriteLiveData.value!!.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktailAdapter = CocktailAdapter(requireContext(), viewModel.favoriteLiveData.value!!)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }

        cocktailAdapter.favoriteCallback = this

        viewModel.mediatorLiveData.observe(requireActivity(), Observer {
            viewModel.favoriteLiveData.value = it.filter { it.isFavorite == true }
            cocktailAdapter.refreshData(viewModel.favoriteLiveData.value!!)
        })

        viewModel.cocktailDBLiveData?.observe(requireActivity(), Observer {
            viewModel.refreshParam()
        })

    }

    companion object {

        fun newInstance(): FavoriteFragment {
            return  FavoriteFragment()
        }
    }

    override fun refreshDB() {
//        viewModel.cocktailDBLiveData?.value =
//                CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
    }


}