package com.slutsenko.cocktailapp.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.db.CocktailDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : Base() {
    lateinit var br: BroadcastReceiver
    lateinit var cocktail: ArrayList<Cocktail>

    override fun myView(): Int {


        return R.layout.activity_main
    }

    override fun activityCreated() {
        br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (cocktail.size > 1) {
                    val randomCocktail = cocktail[Random().nextInt(cocktail.size)]
                    val snackbar = Snackbar.make(frame_layout, "Open ${randomCocktail.strDrink}?", Snackbar.LENGTH_LONG).setAction("OPEN") { v: View? ->
                        val intentRandom = Intent(context, AboutCocktailActivity::class.java)
                        intentRandom.putExtra("cocktail", randomCocktail)
                        context?.startActivity(intentRandom)
                    }.show()
                }
            }
        }
        val filter = IntentFilter()
        filter.addAction("com.slutsenko.action.anotherCocktail")
        registerReceiver(br, filter)


        this@MainActivity.title = "          " + "Cocktail App"
        cocktailDatabase = Room.databaseBuilder(applicationContext,
                CocktailDatabase::class.java, "cocktail5").allowMainThreadQueries().build()
        cocktail = cocktailDatabase?.cocktailDao()?.cocktails as ArrayList<Cocktail>
        if (cocktail.isEmpty()) {
            tv_history.setText(R.string.history)
        } else {
            cocktail.reverse()
            val cocktailAdapter = CocktailAdapter(this@MainActivity, cocktail)
            rv_database.layoutManager = GridLayoutManager(this, COLUMN)
            rv_database.adapter = cocktailAdapter
            tv_history.text = ""
        }
        fab_search.setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, SearchActivity::class.java)) }
    }

    fun showAnotherCocktail(v: View) {
        if (cocktail.size > 1) {
            //var random:Random =
            var toast = Snackbar.make(v, "Look another cocktail", Snackbar.LENGTH_LONG)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()
    }

    companion object {
        private const val COLUMN = 2
        var cocktailDatabase: CocktailDatabase? = null
    }
}