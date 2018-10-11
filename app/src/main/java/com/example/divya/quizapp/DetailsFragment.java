package com.example.divya.quizapp;


import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    TextView quesid;
    TextView quesname;
    SqliteDatabaseHelper database;
    boolean WifiConnected;
    boolean MobileConnected;
    DownloadManager downloadManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        final View view=inflater.inflate(R.layout.fragment_details, container, false);
        final Bundle bundle = this.getArguments();
        quesid=(TextView) view.findViewById(R.id.question_id);

        quesname=(TextView)view.findViewById(R.id.question_select);
        quesid.setText(bundle.getString("Ques_id"));
        quesname.setText(bundle.getString("Ques_text"));
        database=new SqliteDatabaseHelper(getActivity().getApplicationContext());
        final RadioGroup radiogroup =  (RadioGroup)view.findViewById(R.id.grp);
        Button bt = (Button)view.findViewById(R.id.save);
        Button sub=(Button)view.findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = radiogroup .getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)view.findViewById(selectedId);
                String answer=String.valueOf(radioButton.getText());
                database.updateAnswer(answer,bundle.getString("Ques_id"));

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                List_Fragment fragment2 = new List_Fragment();
                FragmentManager fragmentManager =activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag1, fragment2);
                fragmentTransaction.commit();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                database.exportDB(getActivity().getApplicationContext());
                String status = "";
                ConnectivityManager conn_manager = (ConnectivityManager)getActivity().getSystemService(Service.CONNECTIVITY_SERVICE);
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
                if (status == "Not Connected") {
                    Toast.makeText(getActivity().getApplicationContext(),"Not Connected", Toast.LENGTH_SHORT).show();
                    onDestroy();
                }
                else {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Progress_Fragment fragment3 = new Progress_Fragment();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frag1, fragment3);
                    fragmentTransaction.commit();
                }

            }
        });
        return view;
    }


}


