package com.slutsenko.cocktailapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class DrinkService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        try {
            Thread.sleep(3000)
            val another = Intent("com.slutsenko.action.anotherCocktail")
            sendBroadcast(another)
            stopSelf()
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}