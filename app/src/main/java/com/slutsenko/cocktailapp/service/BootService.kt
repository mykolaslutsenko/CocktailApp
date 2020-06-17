package com.slutsenko.cocktailapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.slutsenko.cocktailapp.R


class BootService : Service() {
    private val wait = 1000L
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            for (i in 3 downTo 1) {
                Toast.makeText(this, "${R.string.app_name} start in $i sec", Toast.LENGTH_SHORT).show()
                Thread.sleep(wait)
            }
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