package com.slutsenko.cocktailapp

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.slutsenko.cocktailapp.receiver.AirModeReceiver
import com.slutsenko.cocktailapp.receiver.BatteryStateReceiver
import com.slutsenko.cocktailapp.receiver.StartAppReceiver

abstract class Base : AppCompatActivity() {
    var airModeReceiver= AirModeReceiver()
    val LOG: String = "BaseLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG, this::class.java.toString() + " OnCreate")

        setContentView(myView())
        activityCreated()
    }

    protected abstract fun myView(): Int
    protected abstract fun activityCreated()

    override fun onStart() {
        super.onStart()
        Log.d(LOG, this::class.java.toString() + " OnStart")
    }

    override fun onResume() {
        super.onResume()
        val startAppReceiver = StartAppReceiver()
        registerReceiver(startAppReceiver, IntentFilter("android.intent.action.BOOT_COMPLETED"))

        //val airModeReceiver = AirModeReceiver()
        registerReceiver(airModeReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))

        val batteryStateReceiver = BatteryStateReceiver()
        val filter = IntentFilter()
        filter.addAction("android.intent.action.ACTION_BATTERY_CHANGED")
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
        filter.addAction("android.intent.action.ACTION_BATTERY_LOW")
        filter.addAction("android.intent.action.ACTION_BATTERY_OKAY")
        registerReceiver(batteryStateReceiver, filter)

        Log.d(LOG, this::class.java.toString() + " OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG, this::class.java.toString() + " OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG, this::class.java.toString() + " OnStop")
    }

    override fun onDestroy() {
        unregisterReceiver(airModeReceiver)
        super.onDestroy()
        Log.d(LOG, this::class.java.toString() + " OnDestroy")
    }
}