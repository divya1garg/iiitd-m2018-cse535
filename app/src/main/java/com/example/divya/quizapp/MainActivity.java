package com.example.divya.quizapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmanager=getSupportFragmentManager();
        if(savedInstanceState==null) {
            List_Fragment f1=new List_Fragment();
            fragmanager.beginTransaction().add(R.id.frag1,f1).addToBackStack(null).commit();
        }

    }

}
