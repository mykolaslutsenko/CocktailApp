package com.slutsenko.cocktailapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.slutsenko.cocktailapp.ui.fragment.MainFragment.Companion.ANOTHER_COCKTAIL


class DrinkService : Service() {
    private val wait = 3000L

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        try {
            //Thread.sleep(wait)
            val another = Intent(ANOTHER_COCKTAIL)
            sendBroadcast(another)
            stopSelf()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}