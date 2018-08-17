package com.example.divya.home_assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class registration_a_assignment1_mt18095 extends AppCompatActivity {

    public final String TAG="FirstAPP";
    int state=0;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    Button b_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration__a_assignment1_mt18095);
        Log.d(TAG, "Registration app first activity created");
        Toast.makeText(getApplicationContext(), "firstactivity created", Toast.LENGTH_SHORT).show();
        editText1 = (EditText) findViewById(R.id.textname);
        editText2 = (EditText) findViewById(R.id.textroll);
        editText3 = (EditText) findViewById(R.id.textbranch);
        editText4 = (EditText) findViewById(R.id.course1);
        editText5 = (EditText) findViewById(R.id.course2);
        editText6 = (EditText) findViewById(R.id.course3);
        editText7 = (EditText) findViewById(R.id.course4);
        b_clear=(Button) findViewById(R.id.clear);
        b_clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clear button was clicked", Toast.LENGTH_LONG).show();
                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();
                editText4.getText().clear();
                editText5.getText().clear();
                editText6.getText().clear();
                editText7.getText().clear();
            }
        });
    }
    public void submit(View V)
    {
        String message[]=new String[8];
        Intent intent = new Intent(getApplicationContext(), registration_b_assignment1_mt18095.class);
        Toast.makeText(getApplicationContext(), "Submit button was clicked", Toast.LENGTH_SHORT).show();
        message[0] = editText1.getText().toString();
        message[1] = editText2.getText().toString();
        message[2] = editText3.getText().toString();
        message[3] = editText4.getText().toString();
        message[4] = editText5.getText().toString();
        message[5] = editText6.getText().toString();
        message[6] = editText7.getText().toString();
        if(validationSuccess(message))
        {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }
    public boolean validationSuccess(String message[])
    {
        if(message[0].equalsIgnoreCase(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else  if(!(message[0].matches("[a-zA-Z ]+")))
        {
            Toast.makeText(getApplicationContext(), "name must conatin only alphebetical characters", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(message[1].equalsIgnoreCase(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter rollno", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(message[2].equalsIgnoreCase(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter branch", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((message[3].equalsIgnoreCase(""))&&(message[4].equalsIgnoreCase(""))&&
                (message[5].equalsIgnoreCase(""))&&(message[6].equalsIgnoreCase("")))
        {
            Toast.makeText(getApplicationContext(), "Please enter atleast one course", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void onStart()
    {
        super.onStart();
        Toast.makeText(getApplicationContext(), "firstactivity started", Toast.LENGTH_SHORT).show();
        if (state==6)
            Log.d(TAG,"Status of firstactivity changed from onrestart to start");
        else
            Log.d(TAG,"Status of firstactivity changed from create to start");
        state=1;
    }
    public void onResume()
    {
        super.onResume();
        Toast.makeText(getApplicationContext(), "firstactivity resumed", Toast.LENGTH_SHORT).show();
        if(state==3)
            Log.d(TAG,"Status of firstactivity changed from pause to resume");
        else
            Log.d(TAG,"Status of firstactivity changed from start to resume");
        state=2;
    }
    public void onPause()
    {
        super.onPause();
        Toast.makeText(getApplicationContext(), "firstactivity Paused", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of firstactivity changed from resume to pause");
        state=3;
    }
    public void onStop()
    {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Firstactivity Stopped", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of firstactivity changed from pause to stop");
        state=4;
    }
    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Firstactivity Destroyed", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of firstactivity changed from resume to destroyed");
        state=5;
    }
    public void onRestart()
    {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Firstactivity Restarted", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Status of firstactivity changed from stop to restart");
        state=6;

    }



}
