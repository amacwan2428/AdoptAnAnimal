package com.example.adoptananimal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

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

    // PET TABLE
    static final String PET_TABLE_NAME = "Pet";
    static final String PET_COL1 = "Pet_Id";
    static final String PET_COL2 = "fk_User_Id";
    static final String PET_COL3 = "Pet_Name";
    static final String PET_COL4 = "Pet_Birthdate";
    static final String PET_COL5 = "Pet_Type";


    static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, " + COL3 + " TEXT NOT NULL, " + COL4 + " TEXT NOT NULL, " + COL5 + " TEXT NOT NULL , " + COL6 + " TEXT NOT NULL);";

    static final String CREATE_TABLE_PET = "create table " + PET_TABLE_NAME + " (" + PET_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PET_COL2 + " TEXT NOT NULL, " + PET_COL3 + " TEXT NOT NULL, " + PET_COL4 + " TEXT NOT NULL, " + PET_COL5 + " TEXT NOT NULL );";

    String useremail, userpassword;
    public DBHelper(Context context) {
        super(context,DBNAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_PET);

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

    public Boolean InsertPet(Pet pet) {

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PET_COL2,pet.getName());
        values.put(PET_COL3,pet.getName());
        values.put(PET_COL4,pet.getBirthdate());
        values.put(PET_COL5,pet.getType());
        long result = db.insert(PET_TABLE_NAME,null,values);
        return ((result == -1)?false:true);
    }

    public Boolean LoginUser(String email, String password) {
        useremail = email;
        userpassword = password;
        Log.d("Tag",useremail + userpassword);
        Cursor crsObj = null;
        SQLiteDatabase db = this.getWritableDatabase();
        crsObj = db.rawQuery("select * from " + TABLE_NAME + " WHERE User_Email = " + "'" + useremail + "'",null);
        if(crsObj.moveToFirst()){
            return  true;
        }else{
//            crsObj.moveToFirst();
//            Log.d("Tag",crsObj.getString(3));

            return false;
        }
    }

}
