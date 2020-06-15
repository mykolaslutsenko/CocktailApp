package com.slutsenko.cocktailapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.TextView
import android.widget.Toast
import com.slutsenko.cocktailapp.R
import kotlinx.android.synthetic.main.activity_main.view.*

class BatteryStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val status: Int = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        val level: Int = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale: Int = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

        val batteryPct:Float = level.toFloat() / scale.toFloat()
        //Toast.makeText(context, "Charging $batteryPct $level $scale", Toast.LENGTH_SHORT).show()

        if (isCharging) {
            Toast.makeText(context, "Charging", Toast.LENGTH_SHORT).show()
        }


// How are we charging?
        val chargePlug: Int = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
    }
}