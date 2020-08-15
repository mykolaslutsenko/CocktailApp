package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlin.reflect.KClass


class FavoriteFragment : BaseFragment<MainFragmentViewModel>() {

    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class

    override val contentLayoutResId: Int = R.layout.fragment_favorite
    lateinit var cocktailAdapter: CocktailAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        viewModel.favoriteLiveData.value = viewModel.cocktailDBLiveData.value?.filter { it.isFavorite == true }

        cocktailAdapter = CocktailAdapter(viewModel, requireContext(), viewModel.favoriteLiveData.value
                ?: emptyList())
        rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
        rv_database.adapter = cocktailAdapter

        viewModel.mediatorLiveData.observe(requireActivity(), Observer {
            viewModel.favoriteLiveData.value = it.filter { it.isFavorite == true }
            cocktailAdapter.refreshData(viewModel.favoriteLiveData.value ?: emptyList())
        })
//
//        viewModel.cocktailDBLiveData.observe(requireActivity(), Observer {
//            //viewModel.refreshParam()
//        })
    }

    companion object {

        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}