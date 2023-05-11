package com.example.urhome;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String APART_TABLE = "Apart_Table";
    public static final String COLUMN_APART_NAME = "APART_NAME";
    public static final String COLUMN_APART_PRICE = "APART_PRICE";
    public static final String COLUMN_APART_LOCATION = "APART_LOCATION";

    public static final String COLUMN_APART_ROOMS = "APART_ROOMS";
    public static final String COLUMN_ID = "ID";

    public Database(@Nullable Context context) {
        super(context, "apart.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "Create TABLE " + APART_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_APART_NAME + " TEXT, " + COLUMN_APART_PRICE + " INT, " +COLUMN_APART_LOCATION + " TEXT, " + COLUMN_APART_ROOMS + " INT)" ;
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addOne(apart apartMod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_APART_NAME, apartMod.getName());
        cv.put(COLUMN_APART_PRICE, apartMod.getPrice());
        cv.put(COLUMN_APART_LOCATION, apartMod.getLocation());
        cv.put(COLUMN_APART_ROOMS, apartMod.getRooms());

        long insert = db.insert(APART_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean DeleteOne(apart apartMod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "Delete From " + APART_TABLE + " WHERE " + COLUMN_ID + " = " + apartMod.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            // nothing happens. no one is added.
            return false;
        }
        //close
    }
    public List<apart> getEveryone() {
        List<apart> returnList = new ArrayList<>();
        // get data from database
        String queryString = "Select * from " + APART_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through cursor results
            do {
                int AID = cursor.getInt(0);
                String AName = cursor.getString(1);
                int APrice = cursor.getInt(2);
                String ALocation = cursor.getString(3);
                int ARooms = cursor.getInt(4);

                apart newApart = new apart(AID, AName, APrice ,ALocation,ARooms);
                returnList.add(newApart);
            } while (cursor.moveToNext());
        } else {
            // nothing happens. no one is added.
        }
        //close
        cursor.close();
        db.close();
        return returnList;
    }


}