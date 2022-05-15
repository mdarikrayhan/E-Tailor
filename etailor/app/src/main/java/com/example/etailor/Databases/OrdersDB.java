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

public class OrdersDB extends SQLiteOpenHelper {

    final static  String DBNAME = "orders.db";
    final static  int DBVERSION =1;

    public OrdersDB(@Nullable Context context) {

        super(context, DBNAME, null , DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders "+
                        "(id integer primary key autoincrement,"+
                        "address text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "description text,"+
                        "clothName text,"+
                        "quantity int,"+
                        "email text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String address,String phone,int price,int image,String desc,String clothName,int quantity,String email){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("address",address);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("clothName",clothName);
        values.put("description",desc);
        values.put("quantity",quantity);
        values.put("email",email);
        long id = database.insert("orders",null,values);

        if(id<=0){
            return false;
        }
        else {
            return true;
        }
    }
    public ArrayList<OrdersModel> getOrders(){
        String y=Global.email;
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,clothName,image,price,email from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                String x = cursor.getString(4);
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                if(y.trim().equals(Global.admin.trim())){
                    orders.add(model);
                }
                else if(y.trim().equals(x.trim())){
                    orders.add(model);}
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = "+id,null);
        if(cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String address,String phone,int price,int image,String desc,String clothName,int quantity,int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("address",address);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("clothName",clothName);
        values.put("quantity",quantity);
        long row = database.update("orders",values,"id="+id,null);

        if(row<=0){
            return false;
        }
        else {
            return true;
        }
    }

    public  int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

}
