package com.example.divya.musicapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootRec extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boot Completed", Toast.LENGTH_SHORT).show();

    }
}
