package com.learnitgirl.myvoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.learnitgirl.myvoc.utils.Word;

import java.util.Random;

/**
 * Created by joanna on 22.03.16.
 */
public class DictionaryDatabase {

    private DictionaryDBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DictionaryDatabase(Context context) {
        this.context = context;
    }

    public DictionaryDatabase open() {
        dbHelper = new DictionaryDBHelper(this.context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public boolean insertWord(Word word) {

        ContentValues values = new ContentValues();
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, word.getForeignWord());
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD, word.getNativeWord());
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE, word.getKnowledge());

        long newRowId = db.insert(DictionaryContract.DictionaryEntry.TABLE_NAME, null, values);

        if (newRowId != -1)
            return true;
        else {
            return false;
        }
    }

    public Cursor getWords() {

        String[] args = {};
        Cursor cursor = db.rawQuery(
                "select * from " + DictionaryContract.DictionaryEntry.TABLE_NAME, args);

        return cursor;
    }

    public boolean deleteWord(String id) {

        String[] args = {id};
        if (db.delete(DictionaryContract.DictionaryEntry.TABLE_NAME, DictionaryContract.DictionaryEntry._ID + " = ?", args) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNativeWord(String foreignWord) {
        String[] selectionArgs = {DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, foreignWord};
        Cursor cursor = db.rawQuery(
                "SELECT " + DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD +
                        " FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", selectionArgs);

        cursor.moveToFirst();
        String nativeWord = "";
        int columnIndex = cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD);
        if (columnIndex != -1) {
            nativeWord = cursor.getString(columnIndex);
        }

        return nativeWord;
    }

    public String getForeignWord(String nativeWord) {

        String[] selectionArgs = {DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD, nativeWord};
        Cursor cursor = db.rawQuery(
                "SELECT " + DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD +
                        " FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", selectionArgs);

        cursor.moveToFirst();
        String foreignWord = "";
        int columnIndex = cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD);
        if (columnIndex != -1) {
            foreignWord = cursor.getString(columnIndex);
        }

        return foreignWord;
    }

    public String getRandomForeignWord(){
        Random random = new Random();
        int randomNum = random.nextInt(10);
        String[] selectionArgs = {DictionaryContract.DictionaryEntry._ID, Integer.toString(randomNum)};
        Cursor cursor = db.rawQuery(
                "SELECT " + DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD +
                        " FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", selectionArgs);

        cursor.moveToNext();
        String foreignWord = "";
        int columnIndex = cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD);
        if (columnIndex != -1) {
            foreignWord = cursor.getString(columnIndex);
        }
        return foreignWord;
    }

}
