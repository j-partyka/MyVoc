package com.learnitgirl.myvoc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.learnitgirl.myvoc.database.DictionaryContract.DictionaryEntry;

/**
 * Created by joanna on 26.02.16.
 */
public class DictionaryDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dictionary.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DictionaryEntry.TABLE_NAME + " (" +
                    DictionaryEntry._ID + " INTEGER PRIMARY KEY," +
                    DictionaryEntry.COLUMN_NAME_FOREIGN_WORD + TEXT_TYPE + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_NATIVE_WORD + TEXT_TYPE + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_KNOWLEDGE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DictionaryEntry.TABLE_NAME;

    public DictionaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create table in database
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Upgrades table in database
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
