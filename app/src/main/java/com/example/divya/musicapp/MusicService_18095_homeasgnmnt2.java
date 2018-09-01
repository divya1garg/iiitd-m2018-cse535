package com.example.divya.musicapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MusicService_18095_homeasgnmnt2 extends Service {
    MediaPlayer mPlayer;
    ListView listView;

    public MusicService_18095_homeasgnmnt2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(),"Music Service started", Toast.LENGTH_SHORT).show();
        int resId=intent.getIntExtra("res",0);
        if(mPlayer!=null)
            mPlayer.release();
        mPlayer=MediaPlayer.create(MusicService_18095_homeasgnmnt2.this,resId);
        mPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Service Stopped", Toast.LENGTH_SHORT).show();
        mPlayer.stop();
    }


}
