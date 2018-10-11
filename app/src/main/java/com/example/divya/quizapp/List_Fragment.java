package com.example.divya.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {

    List<String> list;
    ListAdapter adapter;
    MyData d;
    SqliteDatabaseHelper database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        d=new MyData();

        ArrayList<String> ques=new ArrayList<String>();
        database=new SqliteDatabaseHelper(getActivity().getApplicationContext());
        database.insertData("The Language that the computer can understand is called Machine Language.","");
        database.insertData("Magnetic Tape used random access method.","");
        database.insertData("Twitter is an online social networking and blogging service.","");
        database.insertData("Worms and trojan horses are easily detected and eliminated by antivirus software.","");
        database.insertData("GNU / Linux is a open source operating system.","");
        database.insertData("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.","");
        database.insertData("Freeware is software that is available for use at no monetary cost","");
        database.insertData("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.","");
        database.insertData("The hexadecimal number system contains digits from 1 - 15.","");
        database.insertData("Octal number system contains digits from 0 - 7.\n","");
        database.insertData("MS Word is a hardware.","");
        database.insertData("CPU controls only input data of computer.\n","");
        database.insertData("CPU stands for Central Performance Unit.\n","");
        database.insertData("If you want to respond to the sender of a message, click the Respond button.","");
        database.insertData("When you reply to a message, you need to enter the text in the Subject: field.","");
        database.insertData("You can only print one copy of a selected message.","");
        database.insertData(" You cannot preview a message before you print it","");
        database.insertData("There is only one way to print a message.","");
        database.insertData(" When you print a message, it is automatically deleted from your Inbox.","");
        database.insertData("You cannot edit Contact forms.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        database.insertData("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","");
        ques=database.getQuestions();
        View view=inflater.inflate(R.layout.fragment_list_, container, false);
        RecyclerView recycleview=(RecyclerView)view.findViewById(R.id.list_recyclerview);
        d.setQues(ques);
        MyListAdapter myadapter=new MyListAdapter(d);
        recycleview.setAdapter(myadapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recycleview.setLayoutManager(layoutManager);

        return view;
    }


    }



