package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.FragmentFavoriteBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.FavoriteAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlin.reflect.KClass


class FavoriteFragment : BaseFragment<MainFragmentViewModel, FragmentFavoriteBinding>() {

    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class

    override val contentLayoutResId: Int = R.layout.fragment_favorite
    lateinit var cocktailAdapter: FavoriteAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        viewModel.favoriteLiveData.value = viewModel.cocktailDBLiveData.value?.filter { it.isFavorite }
        cocktailAdapter = FavoriteAdapter(viewModel, requireContext(), viewModel.favoriteLiveData.value
                ?: emptyList())
        rv_database.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_database.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        rv_database.adapter = cocktailAdapter

        viewModel.historyLiveData.observe(requireActivity(), Observer {
            viewModel.favoriteLiveData.value = it.filter { it.isFavorite }
            cocktailAdapter.updateList(viewModel.favoriteLiveData.value ?: emptyList())
        })
    }

    companion object {

        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}