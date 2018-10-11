package com.example.divya.quizapp;

import java.util.ArrayList;

public class MyData {

    private ArrayList<String> ques = new ArrayList<String>();

    public  ArrayList<String> getQues() {
        return ques;
    }

    public  void setQues(ArrayList<String> question) {
        ques.clear();
        ques = question;
    }
}
