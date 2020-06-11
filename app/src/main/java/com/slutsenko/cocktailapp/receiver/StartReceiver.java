package com.slutsenko.cocktailapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.slutsenko.cocktailapp.service.StartService;

public class StartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("LOG", "Start reciever");
        context.startService(new Intent(context, StartService.class));

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
