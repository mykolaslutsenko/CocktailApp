package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_history

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        MainFragment.cocktailList = MainFragment.cocktailDatabase?.cocktailDao()?.cocktails as List<Cocktail>
        if (MainFragment.cocktailList.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            //MainFragment.cocktailList.reverse()
            val cocktailAdapter = CocktailAdapter(requireContext(), MainFragment.cocktailList)
            rv_database.layoutManager = GridLayoutManager(context, MainFragment.COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}