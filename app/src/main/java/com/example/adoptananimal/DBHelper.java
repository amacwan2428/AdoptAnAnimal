package com.example.adoptananimal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    static final String DBNAME = "AnimalAdoption.db";
    static final int VERSION = 1;
    static final String TABLE_NAME = "User";
    static final String COL1 = "id";
    static final String COL2 = "User_Name";
    static final String COL3 = "User_Email";
    static final String COL4 = "User_Password";
    static final String COL5 = "User_Address";
    static final String COL6 = "User_Number";

    static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, " + COL3 + " TEXT NOT NULL, " + COL4 + " TEXT NOT NULL, " + COL5 + " TEXT NOT NULL , " + COL6 + " TEXT NOT NULL);";
    public DBHelper(Context context) {
        super(context,DBNAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Boolean InsertUser(UserInformation user) {

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2,user.getUname());
        values.put(COL3,user.getUemail());
        values.put(COL4,user.getUpassword());
        values.put(COL5,user.getUaddress());
        values.put(COL6,user.getUphone());
        long result = db.insert(TABLE_NAME,null,values);
        return ((result == -1)?false:true);
    }
}
