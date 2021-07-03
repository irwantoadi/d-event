package com.irwantostudio.devent.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "event_db.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_IDTASK = "id_task";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "date";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_IDTASK + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_TASK + " TEXT NOT NULL, " +
                COLUMN_NOTE + " TEXT NOT NULL," +
                COLUMN_STATUS + " TEXT NOT NULL," +
                COLUMN_DATE + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_IDTASK, cursor.getString(0));
                map.put(COLUMN_TASK, cursor.getString(1));
                map.put(COLUMN_NOTE, cursor.getString(2));
                map.put(COLUMN_STATUS, cursor.getString(3));
                map.put(COLUMN_DATE, cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public int getTaskPending(){
        String selectQuery = "SELECT * FROM " + TABLE_SQLite
                + " WHERE " + COLUMN_STATUS + "=" + "'Pending'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        Integer jmlCount = 0;
        if (cursor.moveToFirst()) {
            do {
                jmlCount += 1;
            } while (cursor.moveToNext());
        }
        database.close();
        return jmlCount;
    }

    public int getTaskComplete(){
        String selectQuery = "SELECT * FROM " + TABLE_SQLite
                + " WHERE " + COLUMN_STATUS + "=" + "'Complete'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        Integer jmlCount = 0;
        if (cursor.moveToFirst()) {
            do {
                jmlCount += 1;
            } while (cursor.moveToNext());
        }
        database.close();
        return jmlCount;
    }

    public void insert(String task, String note, String status, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (task, note, status, date) " +
                "VALUES ('" + task + "', '" + note + "', '" + status + "', '" + date + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id_task, String task, String note, String status, String date) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLUMN_TASK + "='" + task + "', "
                + COLUMN_NOTE + "='" + note + "', "
                + COLUMN_STATUS + "='" + status + "', "
                + COLUMN_DATE + "='" + date + "'"
                + " WHERE " + COLUMN_IDTASK + "=" + "'" + id_task + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id_task) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_IDTASK + "=" + "'" + id_task + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
