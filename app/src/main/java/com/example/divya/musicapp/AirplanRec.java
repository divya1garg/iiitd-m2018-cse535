package com.example.divya.musicapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplanRec extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        if(isAirplaneModeOn){

            Toast.makeText(context,"Airplane mode is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Airplane mode is off", Toast.LENGTH_SHORT).show();
        }


    }
}
