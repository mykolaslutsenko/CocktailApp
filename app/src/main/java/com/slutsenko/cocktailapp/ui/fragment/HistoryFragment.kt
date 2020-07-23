package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment<MainViewModel>(), CocktailAdapter.OnFavoriteClick {
    override val viewModel: MainViewModel by activityViewModels()
    override val contentLayoutResId: Int = R.layout.fragment_history
    lateinit var cocktailAdapter: CocktailAdapter

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        viewModel.historyLiveData.value = viewModel.cocktailDBLiveData?.value
        cocktailAdapter = CocktailAdapter(requireContext(), viewModel.historyLiveData.value!!)
        if (viewModel.historyLiveData.value!!.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktailAdapter = CocktailAdapter(requireContext(), viewModel.historyLiveData.value!!)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
        cocktailAdapter.favoriteCallback = this

        viewModel.mediatorLiveData.observe(requireActivity(), Observer {
            viewModel.historyLiveData.value = it
            cocktailAdapter.refreshData(viewModel.historyLiveData.value!!)
            viewModel.cocktailQuantityLiveData.value = it.size
        })

        viewModel.cocktailDBLiveData?.observe(requireActivity(), Observer {
            viewModel.refreshParam()
        })
    }

    companion object {

        fun newInstance(): HistoryFragment {

            return HistoryFragment()
        }
    }

    override fun refreshDB() {
        viewModel.cocktailDBLiveData?.value =
                CocktailDatabase.getInstance(requireContext())?.cocktailDao()?.cocktails as List<Cocktail>
    }
}