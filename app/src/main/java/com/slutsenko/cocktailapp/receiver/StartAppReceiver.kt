package com.slutsenko.cocktailapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.slutsenko.cocktailapp.service.StartAppService

class StartAppReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("L", "Receiver")
        context?.startService(Intent(context, StartAppService::class.java))
    }
}