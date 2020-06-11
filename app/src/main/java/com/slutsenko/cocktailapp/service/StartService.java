package com.slutsenko.cocktailapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class StartService extends Service {
//    public StartService() {
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LOG", "service start");
        try {
            for (int i = 3; i > 0; i--) {
                Log.d("LOG", "start " + i);
                Toast.makeText(this, "Додаток запуститься через" + i + "сек", Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Toast.makeText(this, "Додаток запуститься через _ сек", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
