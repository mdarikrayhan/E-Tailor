package com.example.etailor.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.etailor.Models.OrdersModel;

import java.util.ArrayList;

public class LoginDB extends SQLiteOpenHelper
{
    public static final String DBNAME = "Login.db";
    public LoginDB(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, password TEXT,username TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password,String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("username",username);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String getNameByEmail(String email){
        String username = "DefaultName" ;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select username from users where email = ?",new String[]{email});
        if(cursor!=null){
            cursor.moveToFirst();
            username = cursor.getString(0);
        }
        return username;
    }
}
