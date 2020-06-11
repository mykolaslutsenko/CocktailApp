package com.slutsenko.cocktailapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.slutsenko.cocktailapp.receiver.AirplaneModeReceiver;


// No history in manifest
// Intent service
// JobIntentService
// Work manager
public abstract class Base extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(myView());
        activityCreated();
        Log.d(this.getClass().getName(), "onCreate");
    }




    protected abstract int myView();
    protected abstract void activityCreated();


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AirplaneModeReceiver airplaneModeReceiver = new AirplaneModeReceiver();
        this.registerReceiver(airplaneModeReceiver, new IntentFilter("android.intent.action.AIRPLANE_MODE"));
        Log.d(this.getClass().getName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getClass().getName(), "OnStop");
    }
}
