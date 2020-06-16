package com.slutsenko.cocktailapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.SplashActivity

class StartAppService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("L", "Service")
        try {
            for (i in 3 downTo 1) {
                Toast.makeText(this, "${R.string.app_name} start in $i sec", Toast.LENGTH_SHORT).show()
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        //startActivity(Intent(this, SplashActivity::class.java))

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}