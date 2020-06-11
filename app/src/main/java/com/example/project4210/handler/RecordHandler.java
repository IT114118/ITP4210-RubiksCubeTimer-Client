package com.example.project4210.handler;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project4210.models.RecordModel;

import java.util.ArrayList;
import java.util.List;

public class RecordHandler extends SQLiteOpenHelper {
    private static final String DATABASE = "records.db";
    private static final String TABLE_RECORD = "RECORDS";

    public RecordHandler(Context context) {
        super(context, DATABASE, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RECORD +
                " ( rId INTEGER PRIMARY KEY AUTOINCREMENT, rTime REAL NOT NULL, rScramble TEXT NOT NULL, rStar INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(db);
    }

    /// Add a record to database, return its row id, if fails to add return -1
    /// Example:
    // recordHandler.addRecord(new RecordModel(30.0f, "R U F", true));
    // recordHandler.addRecord(new RecordModel(40.0f, "R F F", false));
    // recordHandler.addRecord(new RecordModel(50.0f, "R B F", false));
    public int addRecord(RecordModel recordModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rTime", recordModel.getTime());
        cv.put("rScramble", recordModel.getScramble());
        cv.put("rStar", recordModel.isStarred() ? 1 : 0);
        if (db.insert(TABLE_RECORD, null, cv) != -1) {
            Cursor c = db.rawQuery("SELECT LAST_INSERT_ROWID()", null);
            c.moveToNext();
            return c.getInt(0);
        }

        return -1;
    }

    public void updateRecordStar(int rId, boolean isStarred) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rStar", isStarred ? 1 : 0);
        db.update(TABLE_RECORD, cv, "rId = ?", new String[] { String.valueOf(rId) });
    }

    /// Delete a record
    public boolean deleteRecord(int rId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RECORD, "rId = ?", new String[] { String.valueOf(rId) }) >= 1;
    }

    public void deleteAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECORD, null, null);
    }

    public List<RecordModel> getAllRecords() {
        List<RecordModel> recordModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = getAllRecordsInCursor();
        while (c.moveToNext()) {
            RecordModel recordModel = new RecordModel(c.getFloat(1), c.getString(2), c.getInt(3) >= 1);
            recordModel.setId(c.getInt(0));
            recordModelList.add(recordModel);
        }
        return recordModelList;
    }

    public Cursor getAllRecordsInCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RECORD, null);
    }
}
