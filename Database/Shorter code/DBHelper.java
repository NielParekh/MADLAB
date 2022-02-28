package com.example.x3_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context,"Userdata",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table userdetails(name TEXT primary key, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists userdetails");
    }

    public boolean insert_user(String name, String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);

        long res = DB.insert("userdetails",null,cv);
        if (res ==1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean update_user(String name, String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

//        cv.put("name",name);
        cv.put("phone",phone);

        Cursor curs = DB.rawQuery("Select * from userdetails where name = ?",new String[] {name});

        if (curs.getCount()>0)
        {
            long res = DB.update("userdetails",cv,"name=?",new String[] {name});
            if (res ==-1)
            {
                return false;
            }
            else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }

    public Cursor view_user()
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor curs = DB.rawQuery("select * from userdetails", null);
        return curs;
    }

    public boolean delete_user(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor curs = DB.rawQuery("Select * from userdetails where name = ?",new String[] {name});
        if (curs.getCount()>0)
        {
            long res = DB.delete("userdetails","name=?",new String[] {name});
            if (res ==-1)
            {
                return false;
            }
            else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }

}
