package com.example.adoptananimal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    static final String PET_COL6 = "Pet_Status";



    static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, " + COL3 + " TEXT NOT NULL, " + COL4 + " TEXT NOT NULL, " + COL5 + " TEXT NOT NULL , " + COL6 + " TEXT NOT NULL);";
    static final String ADROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    static final String CREATE_TABLE_PET = "create table " + PET_TABLE_NAME + " (" + PET_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PET_COL2 + " INTEGER, " + PET_COL3 + " TEXT NOT NULL, " + PET_COL4 + " TEXT NOT NULL, " + PET_COL5 + " TEXT NOT NULL,"  + PET_COL6 + " TEXT DEFAULT 'AVAILABLE' ); " ;
    static final String ADROP_TABLE_PET = "DROP TABLE IF EXISTS " + PET_TABLE_NAME;
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
        db.execSQL(ADROP_TABLE);
        db.execSQL(ADROP_TABLE_PET);
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
        /*db.execSQL(ADROP_TABLE);
        db.execSQL(ADROP_TABLE_PET);
        onCreate(db);*/
        ContentValues values = new ContentValues();
        values.put(PET_COL2,pet.getUserId());
        values.put(PET_COL3,pet.getName());
        values.put(PET_COL4,pet.getBirthdate());
        values.put(PET_COL5,pet.getType());
        values.put(PET_COL6,pet.getStatus());
        long result = db.insert(PET_TABLE_NAME,null,values);
        return ((result == -1)?false:true);
    }

    // Returns -1 if no user found and the user_id if sfound a user
    public int LoginUser(String email, String password) {
        useremail = email;
        userpassword = password;
        Log.d("Tag",useremail + userpassword);
        Cursor crsObj = null;
        SQLiteDatabase db = this.getWritableDatabase();
        crsObj = db.rawQuery("select * from " + TABLE_NAME + " WHERE User_Email = " + "'" + useremail + "'",null);
        if(crsObj.moveToFirst()){
            return  crsObj.getInt(0);
        }else{
//            crsObj.moveToFirst();
//            Log.d("Tag",crsObj.getString(3));

            return -1;
        }
    }
    public boolean UpdatePetStatus(Pet pet, String status)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // If status is available
        if(status.equals("AVAILABLE"))
        {
            // Clear User fk by assigning -1
            values.put(PET_COL2,-1);
        }
        // Update status
        values.put(PET_COL6,status);

        long result = db.update(PET_TABLE_NAME,values,PET_COL1 + "=?",new String[]{String.valueOf(pet.getId())});

        return ((result == -1)?false:true);
    }

    public boolean AdoptPet(Pet pet, int user_id)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // If status is available
        // Update status
        values.put(PET_COL2,user_id);
        values.put(PET_COL6,"PENDING");

        long result = db.update(PET_TABLE_NAME,values,PET_COL1 + "=?",new String[]{String.valueOf(pet.getId())});

        return ((result == -1)?false:true);
    }
    public List<Pet> ListPetsByStatus(String status){
        List<Pet> petList = new ArrayList<>();
        // Open db
        SQLiteDatabase db = this.getWritableDatabase();
        // Create cursor
        Cursor cursorObj;
        // Execute select query based on the status and store resulting cursor
        cursorObj = db.rawQuery("SELECT * FROM " + PET_TABLE_NAME + " WHERE " + PET_COL6 + " = '"+status+"';",null);
        // If found entries
        if (cursorObj != null && cursorObj.getCount() != 0)
        {
            // Move cursor to first entry
            cursorObj.moveToFirst();
            // Iterate over all pets found
            do {
                // Create pet db using information obtained from row
                //Pet obj = new Pet(cursorObj.getInt(0),cursorObj.getInt(1),cursorObj.getString(2), cursorObj.getString(3), cursorObj.getString(4),cursorObj.getString(5));
                Pet obj = new Pet();
                obj.setId(cursorObj.getInt(0));
                obj.setUserId(cursorObj.getInt(1));
                obj.setName(cursorObj.getString(2));
                obj.setType(cursorObj.getString(4));
                obj.setBirthdate(cursorObj.getString(3));
                obj.setStatus(cursorObj.getString(5));
                // Add pet to the list
                petList.add(obj);
            }while(cursorObj.moveToNext());
        }
        return petList;
    }

    public Cursor ListPets() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj = db.rawQuery("select * from " + PET_TABLE_NAME, null);
        if (cursorObj != null) {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }


}
