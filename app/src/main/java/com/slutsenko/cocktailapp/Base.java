package com.slutsenko.cocktailapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Base extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(myView());
        activityCreated();
    }

    protected abstract int myView();
    protected abstract void activityCreated();
}
