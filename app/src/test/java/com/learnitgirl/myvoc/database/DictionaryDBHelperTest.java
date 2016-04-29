package com.learnitgirl.myvoc.database;

import android.database.sqlite.SQLiteDatabase;

import com.learnitgirl.myvoc.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by joanna on 01.04.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class DictionaryDBHelperTest {
    DictionaryDBHelper dbHelper;
    @Before
    public void setUp(){
        dbHelper = new DictionaryDBHelper(RuntimeEnvironment.application);
        SQLiteDatabase dbWrite = mock(SQLiteDatabase.class);
        dbHelper.setDBWrite(dbWrite);
    }
    @Test
    public void verifyTheRightWordDeleted(){
        String id = "8";
        dbHelper.deleteWord(id);

        String[] whereArgs = {id};
        verify(dbHelper.dbWrite).delete(DictionaryContract.DictionaryEntry.TABLE_NAME, DictionaryContract.DictionaryEntry._ID + " = ?", whereArgs);
    }

}
