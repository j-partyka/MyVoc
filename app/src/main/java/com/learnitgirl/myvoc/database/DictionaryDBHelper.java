package com.learnitgirl.myvoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.utils.Word;

import java.util.Random;

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

    SQLiteDatabase dbWrite = getWritableDatabase();
    SQLiteDatabase dbRead = getReadableDatabase();

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

    public boolean insertWord(Word word) {

        ContentValues values = new ContentValues();
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, word.getForeignWord());
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD, word.getNativeWord());
        values.put(DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE, word.getKnowledge());

        long newRowId = dbWrite.insert(DictionaryContract.DictionaryEntry.TABLE_NAME, null, values);

        if (newRowId != -1)
            return true;
        else {
            return false;
        }
    }

    private Cursor getWords() {

        String[] whereArgs = new String[]{};
        Cursor cursor = dbRead.rawQuery(
                "select * from " + DictionaryContract.DictionaryEntry.TABLE_NAME, whereArgs);

        return cursor;
    }

    private Cursor getWord(long id) {
        Cursor cursor = getWords();
        cursor.move((int) id);
        return cursor;
    }

    private Word getWordReturnWord(Cursor cursor) {
        String nativeWord = "";
        String foreignWord = "";
        int knowledge = 0;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(DictionaryEntry.COLUMN_NAME_FOREIGN_WORD);
        if (columnIndex != -1) {
            foreignWord = cursor.getString(columnIndex);
        }
        int columnIndex1 = cursor.getColumnIndex(DictionaryEntry.COLUMN_NAME_NATIVE_WORD);
        if (columnIndex1 != -1) {
            nativeWord = cursor.getString(columnIndex1);
        }

        int columnIndex2 = cursor.getColumnIndex(DictionaryEntry.COLUMN_NAME_KNOWLEDGE);
        if (columnIndex2 != -1) {
            knowledge = cursor.getInt(columnIndex2);
        }
        Word word = new Word(foreignWord, nativeWord, knowledge);
        return word;
    }

    public SimpleCursorAdapter getWordsAdapter(Context context) {
        Cursor cursor = getWords();
        cursor.moveToFirst();
//        String[] from = {DictionaryContract.DictionaryEntry._ID, DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD,
//                DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD,
//                DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE};
        String[] from = {DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD,
                DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD,
                DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE};
      //  int[] toViews = {R.id.wordIDTextView, R.id.foreignWordTextView, R.id.nativeWordTextView, R.id.knowledgeTextView};
        int[] toViews = {R.id.foreignWordTextView, R.id.nativeWordTextView, R.id.knowledgeTextView};
        return new SimpleCursorAdapter(context, R.layout.row, cursor, from, toViews, 0);
    }

    public boolean deleteWord(String id) {

        String[] whereArgs = {id};
        if (dbWrite.delete(DictionaryContract.DictionaryEntry.TABLE_NAME, DictionaryContract.DictionaryEntry._ID + " = ?", whereArgs) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNativeWord(String foreignWord) {
        return getWord(foreignWord, DictionaryEntry.COLUMN_NAME_NATIVE_WORD);
    }

    public String getForeignWord(String nativeWord) {

        return getWord(nativeWord, DictionaryEntry.COLUMN_NAME_FOREIGN_WORD);
    }

    public String getWord(String word, String type) {

        String inverseType = DictionaryEntry.COLUMN_NAME_FOREIGN_WORD;

        if (type.equals(DictionaryEntry.COLUMN_NAME_FOREIGN_WORD)){
            inverseType = DictionaryEntry.COLUMN_NAME_NATIVE_WORD;
        }

        String[] whereArgs = new String[]{type, word};
        Cursor cursor = dbRead.rawQuery(
                "SELECT " + inverseType +
                        " FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME +
                        " WHERE ? = ?", whereArgs);

        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(type);
        if (columnIndex != -1) {
            word = cursor.getString(columnIndex);
        }

        return word;
    }

    public String getRandomForeignWord() {
        Random random = new Random();
        int randomNum = random.nextInt(10);
        String[] columns = new String[]{DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD};
        String whereClause = DictionaryContract.DictionaryEntry._ID + " = ? ";
        String[] whereArgs = new String[]{Integer.toString(randomNum)};
        Cursor cursor = dbRead.query(DictionaryContract.DictionaryEntry.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);

        String foreignWord = "Column Index -1";

        if (cursor != null && cursor.moveToFirst()) {
            foreignWord = cursor.getString(cursor.getColumnIndexOrThrow(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD));
        }
        return foreignWord;
    }

    public boolean checkForeignWord(String shownWord, String guessWord) {

        boolean result = false;
        String[] columns = new String[]{DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD};
        String whereClause = DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD + " = ?";
        String[] whereArgs = new String[]{shownWord};
        Cursor cursor = dbRead.query(DictionaryContract.DictionaryEntry.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
        String correctWord = "";
        if (cursor != null && cursor.moveToFirst()) {
            correctWord = cursor.getString(cursor.getColumnIndexOrThrow(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD));
        }
        if (guessWord.equals(correctWord)) {
            result = true;
        }
        return result;
    }

    public void increaseKnowledge(String shownWord) {

        String[] columns = new String[]{DictionaryEntry.COLUMN_NAME_FOREIGN_WORD, DictionaryEntry.COLUMN_NAME_NATIVE_WORD, DictionaryEntry.COLUMN_NAME_KNOWLEDGE};
        String whereClause = DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD + " = ?";
        String[] whereArgs = new String[]{shownWord};

        Cursor cursor = dbWrite.query(DictionaryContract.DictionaryEntry.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);

        Word word = getWordReturnWord(cursor);
        Log.i("TAG", word.getForeignWord());
        Log.i("TAG", word.getNativeWord());
        ContentValues values = new ContentValues();
        values.put(DictionaryEntry.COLUMN_NAME_KNOWLEDGE, word.getKnowledge() + 1);

        dbWrite.update(DictionaryEntry.TABLE_NAME, values, whereClause,whereArgs);
    }

    @VisibleForTesting
    public void setDBWrite(SQLiteDatabase DBWrite) {
        this.dbWrite = DBWrite;
    }
}
