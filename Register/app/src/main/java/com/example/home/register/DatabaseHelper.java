package com.example.home.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_INFO = "Student.db";
    public static final String STUDENT_TABLE = "Student_table.db";
    public static final String col1 = "ID";
    public static final String col2 = "NAME";
    public static final String col3 = "UNIVERSITY ROLL";
    public static final String col4 = "EMAIL";
    public static final String col5 = "PHONE NUMBER";
    public static final String col6 = "DEPARTMENT";
    public static final String col7 = "SEMESTER";
    public static final String col8 = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_INFO , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ STUDENT_TABLE+ "("col1+"INTEGER PRIMARY KEY AUTOINCREMENT,",col2+"TEXT,", col3
                +"INTEGER", col4+ "TEXT", col5+ " INTEGER", col6+ "TEXT",col7 + "TEXT", col8+ "TEXT"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+STUDENT_TABLE);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String name, String roll, String email, String phone, String dept, String sem, String passkey)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,roll);
        contentValues.put(col4,email);
        contentValues.put(col5,phone);
        contentValues.put(col6,dept);
        contentValues.put(col7,sem);
        contentValues.put(col8,passkey);
        long result = sqLiteDatabase.insert(STUDENT_TABLE, null, contentValues);
         if(result==-1)
             return true;
         else
             return false;



    }
}
