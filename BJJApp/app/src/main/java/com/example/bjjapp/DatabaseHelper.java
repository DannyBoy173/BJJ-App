package com.example.bjjapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //deine database schema
    private static final String TABLE_NAME = "notes_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TITLE";
    private static final String COL_3 = "NOTES";
    private static final String COL_4 = "LINKS";
    private static final String COL_5 = "CHAPTER_ID";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the db
        String CreateTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " INTEGER)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(String noteTitle, String notes, String links, Integer chapterID){
        //add note to the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, noteTitle);
        values.put(COL_3, notes);
        values.put(COL_4, links);
        values.put(COL_5, chapterID);
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    public Cursor getData(Integer chapterID){
        //retrieve notes from the database relating to given chapter ID
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE CHAPTER_ID = " + chapterID;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getNote(Integer id) {
        //retrieve specific note data from the database
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
