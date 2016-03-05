package com.learnitgirl.myvoc.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.DictionaryContract;
import com.learnitgirl.myvoc.database.DictionaryDBHelper;

public class DictionaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_activity_dictionary);

        ListView listView = (ListView) findViewById(R.id.dictionary_listview);

        String[] from = {DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD,
                DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD,
                DictionaryContract.DictionaryEntry.COLUMN_NAME_KNOWLEDGE};

        DictionaryDBHelper dbHelper = new DictionaryDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int[] toViews = {R.id.foreignWordTextView, R.id.nativeWordTextView, R.id.knowledgeTextView};

        String[] args = {};

        Cursor cursor = db.rawQuery("SELECT * FROM " + DictionaryContract.DictionaryEntry.TABLE_NAME, args);
        cursor.moveToFirst();
        SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, toViews, 0);

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DictionaryActivity.this, position + 1 + "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
