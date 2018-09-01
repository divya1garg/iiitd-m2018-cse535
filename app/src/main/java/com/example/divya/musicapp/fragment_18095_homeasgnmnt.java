package com.example.divya.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class fragment_18095_homeasgnmnt extends Fragment {


    ImageButton buttonPlay;
    ImageButton buttonStop;
    ImageButton buttoncheck_conn;
    ListView listView;
    List<String> list;
    ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
       View view=inflater.inflate(R.layout.fragment_18095_homeasgnmnt, container, false);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonPlay=(ImageButton) view.findViewById(R.id.play) ;
        buttonStop = (ImageButton) view.findViewById(R.id.stop);
        buttoncheck_conn=(ImageButton) view.findViewById(R.id.check_connection);
        listView=(ListView) view.findViewById(R.id.music_list);

        list=new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for(int i=0;i<fields.length;i++)
        {
            list.add(fields[i].getName());

        }
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int resId=getResources().getIdentifier(list.get(0),"raw",getActivity().getPackageName());
                Intent startIntent = new Intent(getActivity().getApplicationContext(), MusicService_18095_homeasgnmnt2.class);
                startIntent.putExtra("res",resId);
                getActivity().startService(startIntent);


            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent stopIntent = new Intent(getActivity().getApplicationContext(), MusicService_18095_homeasgnmnt2.class);
                getActivity().stopService(stopIntent);
                Intent stopIntent2 = new Intent(getActivity().getApplicationContext(),ConnectActivity_18095_homeassgnmnt2.class);
                getActivity().stopService(stopIntent2);
            }
        });
        buttoncheck_conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity().getApplicationContext(), ConnectActivity_18095_homeassgnmnt2.class);
                getActivity().startService(startIntent);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item=(String)parent.getItemAtPosition(position);
                if (view == null)
                    view = getActivity().getLayoutInflater().inflate(R.layout.simpe_list_item_1, parent, false);
                int resId=getResources().getIdentifier(item,"raw",getActivity().getPackageName());
                Intent startIntent = new Intent(getActivity().getApplicationContext(), MusicService_18095_homeasgnmnt2.class);
                startIntent.putExtra("res",resId);
                getActivity().startService(startIntent);

            }});

    }



}
