package com.example.etailor.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.etailor.Global;

import androidx.annotation.Nullable;

import com.example.etailor.Models.OrdersModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountDB extends SQLiteOpenHelper {

    final static  String DBNAME = "account.db";
    final static  int DBVERSION =1;

    public AccountDB(@Nullable Context context) {

        super(context, DBNAME, null , DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table accounts "+
                        "(email text primary key,"+
                        "collar text,"+
                        "chest text,"+
                        "sleeve text,"+
                        "waist text,"+
                        "insideleg text,"+
                        "outsideleg text,"+
                        "centreebacklenght text)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertAccount(String email,String collar,String chest,String sleeve,String waist,String insideleg,String outsideleg,String centreebacklenght){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("collar",collar);
        values.put("chest",chest);
        values.put("sleeve",sleeve);
        values.put("waist",waist);
        values.put("insideleg",insideleg);
        values.put("outsideleg",outsideleg);
        values.put("centreebacklenght",centreebacklenght);
        long id = database.insert("accounts",null,values);

        if(id<=0){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from accounts where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getAccountByEmail(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from accounts where email = ?", new String[]{email});
        if(cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateAccount(String collar,String chest,String sleeve,String waist,String insideleg,String outsideleg,String centreebacklenght,String email){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("collar",collar);
        values.put("chest",chest);
        values.put("sleeve",sleeve);
        values.put("waist",waist);
        values.put("insideleg",insideleg);
        values.put("outsideleg",outsideleg);
        values.put("centreebacklenght",centreebacklenght);
        long row = database.update("accounts",values,"email = ?",new String[]{email});

        if(row<=0){
            return false;
        }
        else {
            return true;
        }
    }




}
