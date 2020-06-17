package com.slutsenko.cocktailapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BatteryStateReceiver (private val batteryListener: BatteryListener): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        batteryListener.onBatteryChange(intent!!)
    }

    interface BatteryListener{
        fun onBatteryChange(intent: Intent)
    }
}