package com.example.ahmetweighttrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDB extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    // Constructor for login DB
    public SQLiteDB(Context context) {
        super(context, "Login.db", null, 1);
    }

    // Creates the table for login DB
    @Override
    public void onCreate(SQLiteDatabase AccountDB) {
        AccountDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    // Checks if table exists
    @Override
    public void onUpgrade(SQLiteDatabase AccountDB, int i, int i1) {
        AccountDB.execSQL("drop Table if exists users");
    }

    // Method to insert username and password upon registration
    public Boolean insertData(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = DB.insert("users", null, contentValues);
        if (result == -1)   // Checks if record doesn't exist
            return false;
        else
            return true;
    }

    // Method to check if username exists in database already
    public Boolean userCheck(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0) {
            return true;
        }
        else
            return false;
    }

    // Method to check the password in DB upon login attempt
    public Boolean passwordCheck(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }
}
