package com.slutsenko.cocktailapp.ui

import android.content.Intent
import android.os.Bundle
import com.slutsenko.cocktailapp.BaseActivity
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityMainBinding
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.impl.FilterResultCallback
import com.slutsenko.cocktailapp.receiver.BatteryStateReceiver
import com.slutsenko.cocktailapp.ui.fragment.FilterFragment
import com.slutsenko.cocktailapp.ui.fragment.MainFragment
import com.slutsenko.cocktailapp.ui.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding>(), BatteryStateReceiver.BatteryListener,
        FilterFragment.OnFilterResultListener, FilterResultCallback {
    //lateinit var br: BroadcastReceiver
    // lateinit var batteryStateReceiver: BatteryStateReceiver


    override fun myView(): Int {

        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainFragment = MainFragment.newInstance()
        val profileFragment = ProfileFragment.newInstance()


        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(profileFragment)
                            .show(mainFragment)
                            .commit()
                    true
                }
                R.id.menu_profile -> {
                    supportFragmentManager
                            .beginTransaction()
                            .hide(mainFragment)
                            .show(profileFragment)
                            .commit()
                    true
                }
                else -> false
            }

        }
        supportFragmentManager.beginTransaction().replace(R.id.fcv_main, profileFragment, ProfileFragment::class.java.simpleName).hide(profileFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fcv_main, mainFragment, MainFragment::class.java.simpleName).commit()

    }

    override fun activityCreated() {



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

    override fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?) {
        callbacks.forEach {
            it.onFilterResult(alcoholFilter, categoryFilter)
        }
    }

    override val callbacks: HashSet<FilterFragment.OnFilterResultListener> = hashSetOf()


    override fun addCallBack(listener: FilterFragment.OnFilterResultListener) {
        callbacks.add(listener)
    }

    override fun removeCallBack(listener: FilterFragment.OnFilterResultListener) {
        callbacks.remove(listener)
    }

}