package com.slutsenko.cocktailapp.presentation.ui.activity

import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivitySearchBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.fragment.MainFragment.Companion.COLUMN
import com.slutsenko.cocktailapp.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlin.reflect.KClass


class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override val viewModelClass: KClass<SearchViewModel>
        get() = SearchViewModel::class

    var cocktailAdapter: CocktailAdapter? = null

    override fun myView(): Int {
        return R.layout.activity_search
    }

    override fun activityCreated() {
        cocktailAdapter = CocktailAdapter(
                null,
                this@SearchActivity,
                viewModel.searchResultCocktailListLiveData.value ?: emptyList())
        rv_search.layoutManager = GridLayoutManager(this, COLUMN)
        rv_search.adapter = cocktailAdapter

        viewModel.searchResultCocktailListLiveData.observe(this, Observer {
            cocktailAdapter?.refreshData(it)
        })

        sv_toolbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return if (!query.isNullOrEmpty()) {
                    viewModel.searchQueryLiveData.value = query.trim()
                    return true
                } else {
                    false
                }
            }
        })
    }

    override fun configureDataBinding(binding: ActivitySearchBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }
}