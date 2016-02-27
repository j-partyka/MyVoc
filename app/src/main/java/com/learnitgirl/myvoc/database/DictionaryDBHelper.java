package com.learnitgirl.myvoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            "CREATE TABLE " + DictionaryContract.DictionaryEntry.TABLE_NAME + " (" +
                    DictionaryContract.DictionaryEntry._ID + " INTEGER PRIMARY KEY," +
                    DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD + TEXT_TYPE + COMMA_SEP +
                    DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD + TEXT_TYPE + COMMA_SEP +
                    DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DictionaryContract.DictionaryEntry.TABLE_NAME;

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

    /**
     * Insert values to databaseg
     *
     * @param foreignWord
     * @param nativeWord
     * @param knowledge
     * @return true when the insert is success, false in other case
     */
    public boolean insert(String foreignWord, String nativeWord, String knowledge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, foreignWord);
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD, nativeWord);
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE, knowledge);

        long newRowId = db.insert(DictionaryContract.DictionaryEntry.TABLE_NAME, null, values);

        if (newRowId != -1)
            return true;
        else {
            return false;
        }
    }

    public String getForeignWord(String nativeWord) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD
        };

        String[] selectionArgs = {DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD, nativeWord};
        Cursor cursor = db.rawQuery(
                "SELECT " + DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD +
                        " FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", selectionArgs);

        cursor.moveToFirst();
        String foreignWord = cursor.getString(cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD));

        return foreignWord;
    }

    public String getNativeWord(String foreignWord) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD
        };

        String[] selectionArgs = {DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, foreignWord};
        Cursor cursor = db.rawQuery(
                "SELECT " + DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD +
                        " FROM" + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", selectionArgs);

        cursor.moveToFirst();
        String nativeWord = cursor.getString(cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD));

        return nativeWord;
    }
}
