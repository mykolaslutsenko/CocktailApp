package com.slutsenko.cocktailapp.ui

import android.content.Intent
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.receiver.BatteryStateReceiver
import com.slutsenko.cocktailapp.ui.fragment.FilterFragment
import com.slutsenko.cocktailapp.ui.presentation.adapter.page.FavoritePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Base(), BatteryStateReceiver.BatteryListener {
    //lateinit var br: BroadcastReceiver
    // lateinit var batteryStateReceiver: BatteryStateReceiver


    override fun myView(): Int {

        return R.layout.activity_main
    }

    override fun activityCreated() {
        viewpager2.adapter = FavoritePagerAdapter(this)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            when (position) {
                0 -> tab.text = "History"
                else -> tab.text = "Favorite"
            }
        }.attach()

        //supportFragmentManager.beginTransaction().add(R.id.main_container, MainFragment::class.java, null).addToBackStack("Name").commit()

//        br = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                if (cocktail.size > 1) {
//                    val randomCocktail = cocktail[Random().nextInt(cocktail.size)]
//                    Snackbar.make(frame_layout, "Open ${randomCocktail.strDrink}?",
//                            Snackbar.LENGTH_LONG).setAction("OPEN") {
//                        val intentRandom = Intent(context, AboutCocktailActivity::class.java)
//                        intentRandom.putExtra("cocktail", randomCocktail)
//                        context?.startActivity(intentRandom)
//                    }.show()
//                }
//            }
//        }
//        val filter = IntentFilter()
//        filter.addAction(ANOTHER_COCKTAIL)
//        registerReceiver(br, filter)


        //this@MainActivity.title = "          " + "Cocktail App"
    }

    fun onClickFilter(view: View) {
        supportFragmentManager.beginTransaction().
        add(R.id.main_container, FilterFragment::class.java, null).
        commit()
    }





    override fun onResume() {
//        batteryStateReceiver = BatteryStateReceiver(this)
//        val filter = IntentFilter()
//        filter.addAction("android.intent.action.ACTION_BATTERY_CHANGED")
//        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
//        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
//        filter.addAction("android.intent.action.ACTION_BATTERY_LOW")
//        filter.addAction("android.intent.action.ACTION_BATTERY_OKAY")
//        registerReceiver(batteryStateReceiver, filter)
        super.onResume()
    }

    override fun onDestroy() {
//        unregisterReceiver(br)
//        unregisterReceiver(batteryStateReceiver)
        super.onDestroy()
    }


    override fun onBatteryChange(intent: Intent) {
//        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
//        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
//        val percent = (level * 100 / scale.toFloat()).toInt().toString()
//        tv_battery.text = percent
//
//        when (intent.action) {
//            Intent.ACTION_POWER_CONNECTED -> {
//                tv_battery.visibility = View.VISIBLE
//                tv_battery.setBackgroundColor(Color.WHITE)
//                tv_battery.text = percent
//            }
//            Intent.ACTION_POWER_DISCONNECTED -> {
//                tv_battery.visibility = View.GONE
//            }
//            Intent.ACTION_BATTERY_OKAY -> {
//                tv_battery.setBackgroundColor(Color.GREEN)
//                tv_battery.text = percent
//            }
//            Intent.ACTION_BATTERY_LOW -> {
//                tv_battery.setBackgroundColor(Color.RED)
//                tv_battery.text = percent
//            }
//            Intent.ACTION_BATTERY_CHANGED -> {
//                tv_battery.setBackgroundColor(Color.BLACK)
//                tv_battery.text = percent
//            }
//        }
    }
}