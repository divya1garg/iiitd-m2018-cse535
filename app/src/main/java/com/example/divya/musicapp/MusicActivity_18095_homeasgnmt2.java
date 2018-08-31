package com.example.divya.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity_18095_homeasgnmt2 extends AppCompatActivity {

    FragmentManager fragmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_18095_homeasgnmt2);
        fragmanager=getSupportFragmentManager();
        if(savedInstanceState==null) {
            fragment_18095_homeasgnmnt f1=new fragment_18095_homeasgnmnt();
            fragmanager.beginTransaction().add(R.id.frag1,f1).addToBackStack(null).commit();
        }


    }

}
