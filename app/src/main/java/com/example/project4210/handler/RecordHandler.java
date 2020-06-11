package com.example.project4210.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordHandler extends SQLiteOpenHelper {
    private static final String DATABASE = "records.db";
    private static final String TABLE_RECORD = "RECORDS";

    public RecordHandler(Context context) {
        super(context, DATABASE, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RECORD +
                " ( rId INTEGER PRIMARY KEY AUTOINCREMENT, rTime TEXT NOT NULL, rScramble TEXT NOT NULL, rStar TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }





}
