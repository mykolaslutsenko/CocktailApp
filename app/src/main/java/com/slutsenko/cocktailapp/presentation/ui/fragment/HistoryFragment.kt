package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.extension.log
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

//        viewModel.historyLiveData.value = viewModel.cocktailDBLiveData.value

        cocktailAdapter = CocktailAdapter(viewModel, requireContext(), viewModel.mediatorLiveData.value
                ?: emptyList())
        rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
        rv_database.adapter = cocktailAdapter

        viewModel.mediatorLiveData.observe(requireActivity(), Observer {
//            viewModel.historyLiveData.value = it
            "mediator".log
//            it.size.log
            cocktailAdapter.refreshData(it)
            viewModel.cocktailQuantityLiveData.value = it.size
        })

//        viewModel.cocktailDBLiveData.observe(requireActivity(), Observer {
//            //viewModel.refreshParam()
////            viewModel.historyLiveData.value = it
////            cocktailAdapter.refreshData(viewModel.historyLiveData.value!!)
//        })
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}

