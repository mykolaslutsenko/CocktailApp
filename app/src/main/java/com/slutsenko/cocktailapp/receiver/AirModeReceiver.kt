package com.slutsenko.cocktailapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val airModeOn = intent!!.getBooleanExtra("state", false)
        if (airModeOn) {
            Toast.makeText(context, "Смакуйте маргаритку із відчуттям міри." +
                    " І надіємося, що Ви не пілот під  час польоту", Toast.LENGTH_SHORT).show()
        }
    }
}