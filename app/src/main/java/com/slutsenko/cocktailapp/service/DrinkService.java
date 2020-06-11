package com.slutsenko.cocktailapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

public class DrinkService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try{
            for (int i=0; i<3; i++) {
                Log.d("LOG", "OLOLO");
                Thread.sleep(3000);
            }

        }  catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
