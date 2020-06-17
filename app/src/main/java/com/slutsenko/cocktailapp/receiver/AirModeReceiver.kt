package com.slutsenko.cocktailapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.slutsenko.cocktailapp.R

class AirModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val airModeOn = intent!!.getBooleanExtra("state", false)
        if (airModeOn) {
            Toast.makeText(context, R.string.toast_airmode, Toast.LENGTH_SHORT).show()
        }
    }
}