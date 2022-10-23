package com.example.tugas10_liblary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";


    private static final String CREATE_TABLE_STRUDENTS = "CREATE TABLE " +
            TABLE_STUDENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_FIRSTNAME
            + " TEXT );";

    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STRUDENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '"+TABLE_STUDENTS+"'");
        onCreate(db);
    }
    public long addStudentDetail (String student){
        SQLiteDatabase db = this.getWritableDatabase();
        //buat content val
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME,student);
        long insert = db.insert(TABLE_STUDENTS,null,values);
        return insert;
    }
    @SuppressLint("Range")
    public ArrayList<String> getAllStudentList(){
        ArrayList<String> studentList = new ArrayList<String>();
        String name ="";
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                name = cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME));
                studentList.add(name);
            }while (cursor.moveToNext());
            Log.d("array", String.valueOf(studentList.toArray()));
        }
        return studentList;
    }
}
