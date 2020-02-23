package com.example.dresser_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "clothes.db";
    public static final String TABLE_NAME = "clothes_table.db";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ADRESS";
    public static final String COL_3 = "TYPE";
    public static final String COL_4 = "COLOR";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table " +  TABLE_NAME + " (ID STRING PRIMARY KEY AUTOINCREMENT, ADRESS TEXT, TPYE TEXT, COLOR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String adress, String type, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_2, adress);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, color);
        long result =  db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor searchColor(String color) //work in progress
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME+ " WHERE "+COL_4+ "Like '%"+color+"%'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}



