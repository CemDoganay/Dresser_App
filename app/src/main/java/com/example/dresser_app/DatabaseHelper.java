package com.example.dresser_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "clothes.db";
    public static final String TABLE_NAME = "clothes_table";
    public static final String COL_1 = "CLOTH_ID";
    public static final String COL_2 = "ADDRESS";
    public static final String COL_3 = "TYPE";
    public static final String COL_4 = "COLOR";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table " + TABLE_NAME + " (CLOTH_ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS TEXT, TYPE TEXT, COLOR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String address, String type, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, address);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, color);
        long result =  db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    /*
    public Cursor searchColor(String color, String type) //work in progress
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_2 + " FROM "+ TABLE_NAME+ " WHERE "+ COL_4 + " LIKE '%"+color+"%' AND " + COL_3 + " LIKE '" + type + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
     */


    public Cursor getPicInfo(String picName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_3 +", " + COL_4 + " FROM "+ TABLE_NAME + " WHERE "+ COL_2 + " = '" + picName + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getMatchingCloth(String matchingClothColor, String matchingType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_2 + " FROM " + TABLE_NAME + " WHERE " + COL_4 + " = '" + matchingClothColor.toUpperCase() + "' AND " + COL_3 + " = '" + matchingType + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
