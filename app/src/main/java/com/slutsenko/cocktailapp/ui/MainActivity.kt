package com.slutsenko.cocktailapp.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.BatteryManager
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.receiver.BatteryStateReceiver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : Base(), BatteryStateReceiver.BatteryListener {
    lateinit var br: BroadcastReceiver
    lateinit var batteryStateReceiver: BatteryStateReceiver
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


    override fun onResume() {
        batteryStateReceiver = BatteryStateReceiver(this)
        val filter = IntentFilter()
        filter.addAction("android.intent.action.ACTION_BATTERY_CHANGED")
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
        filter.addAction("android.intent.action.ACTION_BATTERY_LOW")
        filter.addAction("android.intent.action.ACTION_BATTERY_OKAY")
        registerReceiver(batteryStateReceiver, filter)
        super.onResume()
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        unregisterReceiver(batteryStateReceiver)
        super.onDestroy()
    }

    companion object {
        private const val COLUMN = 2
        var cocktailDatabase: CocktailDatabase? = null
    }

    override fun onBatteryChange(intent: Intent) {
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val percent = (level * 100 / scale.toFloat()).toInt().toString()
        tv_battery.text = percent

        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                tv_battery.visibility = View.VISIBLE
                tv_battery.setBackgroundColor(Color.WHITE)
                tv_battery.setText(percent)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                tv_battery.visibility = View.GONE
            }
            Intent.ACTION_BATTERY_OKAY -> {
                tv_battery.setBackgroundColor(Color.GREEN)
                tv_battery.setText(percent)
            }
            Intent.ACTION_BATTERY_LOW -> {
                tv_battery.setBackgroundColor(Color.RED)
                tv_battery.setText(percent)
            }
            Intent.ACTION_BATTERY_CHANGED -> {
                tv_battery.setBackgroundColor(Color.BLACK)
                tv_battery.setText(percent)
            }
        }
//        tv_battery.setBackgroundColor(Color.YELLOW)
//        tv_battery.setTextColor(Color.BLUE)
    }
}