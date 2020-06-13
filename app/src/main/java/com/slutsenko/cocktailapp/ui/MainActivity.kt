package com.slutsenko.cocktailapp.ui

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.db.CocktailDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Base() {

    override fun myView(): Int {
        return R.layout.activity_main
    }

    override fun activityCreated() {
        this@MainActivity.title = "          " + "Cocktail App"
        cocktailDatabase = Room.databaseBuilder(applicationContext,
                CocktailDatabase::class.java, "cocktail1").allowMainThreadQueries().build()
        val cocktail = cocktailDatabase?.cocktailDao()?.cocktails as ArrayList<Cocktail>
        if (cocktail.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktail.reverse()
            val cocktailAdapter = CocktailAdapter(this@MainActivity, cocktail)
            rv_database.layoutManager = GridLayoutManager(this, COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
        fab_search.setOnClickListener{
            v: View? -> startActivity(Intent(this@MainActivity, SearchActivity::class.java)) }
    }

    companion object {
        private const val COLUMN = 2
        var cocktailDatabase: CocktailDatabase? = null
    }
}