package com.example.divya.home_assignment_1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class registration_b_assignment1_mt18095 extends AppCompatActivity {

    public final String TAG="FirstAPP";
    int state=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__b_assignment1_mt18095);
        Log.d(TAG, "Registration app second activity created");
        Toast.makeText(getApplicationContext(), "secondactivity created", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String message[] = intent.getStringArrayExtra(EXTRA_MESSAGE);
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textname);
        textView.setText(message[0]);
        TextView textroll=findViewById(R.id.textroll);
        textroll.setText(message[1]);
        TextView textbranch=findViewById(R.id.textbranch);
        textbranch.setText(message[2]);
        TextView textcourse1=findViewById(R.id.course1);
        textcourse1.setText(message[3]);
        TextView textcourse2=findViewById(R.id.course2);
        textcourse2.setText(message[4]);
        TextView textcourse3=findViewById(R.id.course3);
        textcourse3.setText(message[5]);
        TextView textcourse4=findViewById(R.id.course4);
        textcourse4.setText(message[6]);

    }
    public void onStart()
    {
        super.onStart();
        Toast.makeText(getApplicationContext(), "secondactivity started", Toast.LENGTH_SHORT).show();
        if (state==6)
            Log.d(TAG,"Status of secondactivity changed from onrestart to start");
        else
            Log.d(TAG,"Status of secondactivity changed from create to start");
        state=1;
    }
    public void onResume()
    {
        super.onResume();
        Toast.makeText(getApplicationContext(), "secondactivity resumed", Toast.LENGTH_SHORT).show();
        if(state==3)
            Log.d(TAG,"Status of secondactivity changed from pause to resume");
        else
            Log.d(TAG,"Status of secondactivity changed from start to resume");
        state=2;
    }
    public void onPause()
    {
        super.onPause();
        Toast.makeText(getApplicationContext(), "secondactivity Paused", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of secondactivity changed from resume to pause");
        state=3;
    }
    public void onStop()
    {
        super.onStop();
        Toast.makeText(getApplicationContext(), "secondactivity Stopped", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of secondactivity changed from resume to stop");
        state=4;
    }
    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "secondactivity Destroyed", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of secondactivity changed from resume to destroyed");
        state=5;
    }
    public void onRestart()
    {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "secondactivity Restarted", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of secondactivity changed from stop to restart");
        state=6;

    }

}