package com.example.divya.quizapp;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_PICTURES;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME="Question1.db";
    public static int DATABASE_VERSION=1;

    public SqliteDatabaseHelper( Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }

    @Override
    public void onCreate(SQLiteDatabase db) {


        Log.e("MyApp", "onCreate invoked");
        // Tables creation queries
        String CREATE_EVENT_TABLE = "create table if not exists " + "Questions" + "(" + "Question_id" + " integer primary key, "
                + "Question" + " text, "
                + "Answer" + " text)";

        // Creating tables
        db.execSQL(CREATE_EVENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Questions" );
        onCreate(db);

    }

    public void selectData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT * FROM Questions" ;
        Cursor c = db.rawQuery(sqlQuery, null);

            if (c.moveToFirst()){
                do{
                    Log.d("selet afte", c.getString(c.getColumnIndex("Question"))+c.getString(c.getColumnIndex("Answer")));
                }while(c.moveToNext());
            }
            c.close();
            }

    public void insertData(String question_given,String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT * FROM Questions" ;
        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null && c.getCount() ==30) {



            }
        else
        {
        Log.e("MyApp", "oninsert invoked");
        SQLiteStatement stmt = db.compileStatement("INSERT INTO Questions (Question,Answer) " +
                "VALUES (?,?)");
        stmt.bindString(1,question_given);
        stmt.bindString(2, answer);
        stmt.execute();
        stmt.close();
        db.close();}
    }

    public ArrayList getQuestions() {
        Log.e("MyApp", "ongetting invoked");
        ArrayList Questions = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Question from Questions ORDER BY Question_id ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String question=cursor.getString(0);
            Questions.add(question);
        }
        db.close();
        return Questions;
    }

    public void updateAnswer(String answer,String ques_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Answer",answer);
        String whereClause = "Question_id=?";
        String[] whereArgs = new String[] {String.valueOf(ques_id)};
        db.update("Questions", contentValues, whereClause, whereArgs);
        selectData();

    }


    public void exportDB(Context context) {

        PrintWriter printWriter = null;


        Log.d("divya","save database");
        SQLiteDatabase db = this.getWritableDatabase();

        File exportDir = new File(context.getApplicationContext().getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS), "csv");

        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            printWriter = new PrintWriter(new FileWriter(file));
            Cursor curCSV = db.rawQuery("SELECT * FROM Questions",null);
            printWriter.println("Questions");
            printWriter.println("Question_id,Question,Answer");
            while(curCSV.moveToNext())
            {
                int id = curCSV.getInt(curCSV.getColumnIndex("Question_id"));
                String Question = curCSV.getString(curCSV.getColumnIndex("Question"));
                String Answer = curCSV.getString(curCSV.getColumnIndex("Answer"));

                String record = String.valueOf(id) + "," +"\""+Question+"\"" + "," +Answer;
                printWriter.println(record); //write the record in the .csv file
            }
            curCSV.close();
            printWriter.flush();
            printWriter.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
        Log.d("divya","save database suhdbb");
    }


}
