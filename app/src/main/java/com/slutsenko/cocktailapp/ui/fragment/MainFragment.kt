package com.slutsenko.cocktailapp.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room

import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.Cocktail

import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.ui.SearchActivity
import kotlinx.android.synthetic.main.fragment_main.*



class MainFragment : BaseFragment() {
    lateinit var cocktail: ArrayList<Cocktail>

    override val contentLayoutResId: Int = R.layout.fragment_main

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        fab_search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        cocktailDatabase = Room.databaseBuilder(requireContext(),
                CocktailDatabase::class.java, "cocktail5").allowMainThreadQueries().build()
        cocktail = cocktailDatabase?.cocktailDao()?.cocktails as ArrayList<Cocktail>
        //cocktail.filter { it.strAlcoholic -> "Alcoholic" }
        if (cocktail.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktail.reverse()
            val cocktailAdapter = CocktailAdapter(requireContext(), cocktail)
            rv_database.layoutManager = GridLayoutManager(context, COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
    }

    override fun onBottomSheetDialogFragmentClick(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }

    override fun onBottomSheetDialogFragmentDismiss(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = MainFragment()
        const val ANOTHER_COCKTAIL = "com.slutsenko.action.anotherCocktail"
        const val COLUMN = 2
        var cocktailDatabase: CocktailDatabase? = null
    }

}