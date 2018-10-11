package com.example.divya.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyListAdapter extends RecyclerView.Adapter {

    MyData d=new MyData();
    MyListAdapter(MyData d1)
    {
        d.setQues(d1.getQues());
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((listViewHolder) holder).bindView(i);

    }

    @Override
    public int getItemCount() {
        return d.getQues().size();
    }

    private class listViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView question;
        private TextView question_id;

        private listViewHolder(View itemView)
        {
            super(itemView);
            question=(TextView)itemView.findViewById(R.id.item_text);
            question_id=(TextView)itemView.findViewById(R.id.question_id);
            itemView.setOnClickListener(this);
        }
        public void bindView(int postion)
        {
            question.setText(d.getQues().get(postion));
            question_id.setText(String.valueOf(postion+1));
        }
        public void onClick(View view)
        {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            DetailsFragment fragment2 = new DetailsFragment();
            FragmentManager fragmentManager =activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle=new Bundle();
            bundle.putString("Ques_text", String.valueOf(question.getText()));
            bundle.putString("Ques_id", String.valueOf(question_id.getText()));
            fragment2.setArguments(bundle);
            fragmentTransaction.replace(R.id.frag1, fragment2);
            fragmentTransaction.commit();
        }
    }
}
