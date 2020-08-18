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

class HistoryFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_history
    lateinit var cocktailAdapter: CocktailAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        cocktailAdapter = CocktailAdapter(viewModel, requireContext(), viewModel.historyLiveData.value
                ?: emptyList())
        rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
        rv_database.adapter = cocktailAdapter

        viewModel.historyLiveData.observe(requireActivity(), Observer {
            cocktailAdapter.refreshData(it)
            viewModel.cocktailQuantityLiveData.value = it.size
        })
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}

