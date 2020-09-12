package com.slutsenko.cocktailapp.feature.main.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.FragmentHistoryBinding
import com.slutsenko.cocktailapp.feature.main.MainFragmentViewModel
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.reflect.KClass

class HistoryFragment : BaseFragment<MainFragmentViewModel, FragmentHistoryBinding>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_history
    lateinit var cocktailAdapter: CocktailAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        cocktailAdapter = CocktailAdapter(
                viewModel,
                requireContext(),
                viewModel.historyLiveData.value ?: emptyList(),
                rv_database
               )
        //rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
        rv_database.addItemDecoration(CocktailAdapter.CardViewDecorator())
        rv_database.adapter = cocktailAdapter

        viewModel.historyLiveData.observe(requireActivity(), Observer {
            cocktailAdapter.updateList(it)
            viewModel.cocktailQuantityLiveData.value = it.size
        })
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}

