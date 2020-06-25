package com.slutsenko.cocktailapp.ui

import android.content.Intent
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.impl.FilterResultCallback
import com.slutsenko.cocktailapp.receiver.BatteryStateReceiver
import com.slutsenko.cocktailapp.ui.fragment.FilterFragment
import com.slutsenko.cocktailapp.ui.fragment.MainFragment


class MainActivity : Base(), BatteryStateReceiver.BatteryListener,
        FilterFragment.OnFilterResultListener, FilterResultCallback {
    //lateinit var br: BroadcastReceiver
    // lateinit var batteryStateReceiver: BatteryStateReceiver

    var callback : FilterFragment.OnFilterResultListener? = null
    override fun myView(): Int {

        return R.layout.activity_main
    }

    override fun activityCreated() {


        supportFragmentManager.beginTransaction().add(R.id.fcv_main, MainFragment::class.java, null).commit()

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

    }

    fun onClickFilter(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {

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

    override fun onFilterApply(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        //iv_indicator.visibility = View.VISIBLE
    }

    override fun onFilterReset(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        //iv_indicator.visibility = View.GONE
    }

    override fun addCallBack(listener: FilterFragment.OnFilterResultListener) {

    }

    override fun removeCallBack(listener: FilterFragment.OnFilterResultListener) {

    }


}