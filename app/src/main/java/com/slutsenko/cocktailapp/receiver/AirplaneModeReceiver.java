package com.slutsenko.cocktailapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Objects;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //if (Objects.requireNonNull(intent.getAction()).equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            Toast.makeText(context, "Смакуйте маргаритку із відчуттям міри." +
                    " І надіємося, що Ви не пілот під  час польоту", Toast.LENGTH_SHORT).show();
       // }
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
