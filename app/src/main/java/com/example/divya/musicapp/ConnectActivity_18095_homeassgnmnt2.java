package com.example.divya.musicapp;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.Environment.DIRECTORY_MUSIC;

public class ConnectActivity_18095_homeassgnmnt2 extends Service {

    boolean WifiConnected;
    boolean MobileConnected;
    TextView connect;
    MediaPlayer mPlayer;
    DownloadManager downloadManager;
    public  ConnectActivity_18095_homeassgnmnt2() {
    }


    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        checkConnection();
        return super.onStartCommand(intent, flags, startId);
    }

    public String checkConnection() {

        String status = "";
        ConnectivityManager conn_manager = (ConnectivityManager) getSystemService(Service.CONNECTIVITY_SERVICE);
        NetworkInfo nwinfo = conn_manager.getActiveNetworkInfo();
        if ((nwinfo != null) && (nwinfo.isConnected())) {
            WifiConnected = nwinfo.getType() == ConnectivityManager.TYPE_WIFI;
            MobileConnected = nwinfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (WifiConnected)
                status = "wifi connected";
            else {
                if (MobileConnected)
                    status = "Mobile Connected";
            }

        }
        if (status == "") {
            status = "Not Connected";
        }
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status == "Not Connected")
            onDestroy();
        if (status != "Not Connected")
        {
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "s1.mp3");
            Long reference = downloadManager.enqueue(request);
        }
        File sub = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "s1.mp3");

        Uri myUri1 = Uri.fromFile(sub);
          mPlayer = new MediaPlayer();
        try {
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(getApplicationContext(), myUri1);
            mPlayer.prepare();
            mPlayer.start();
        }catch(Exception e){}

        return status;
    }
    @Override
    public void onDestroy() {
            super.onDestroy();
            mPlayer.stop();
        }
}

