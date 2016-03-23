package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
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
        DictionaryDBHelper dbHelper = new DictionaryDBHelper(this);

        listView.setAdapter(dbHelper.getWordsAdapter(this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DictionaryActivity.this, position + 1 + "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        String msg = "";

        switch (item.getItemId()) {
            case R.id.action_learn:
                intent = new Intent(this, LearnActivity.class);
                startActivity(intent);
                break;
            case R.id.action_new_word:
                intent = new Intent(this, AddNewWordActivity.class);
                startActivity(intent);
                break;
            case R.id.action_dictionary:
                intent = new Intent(this, DictionaryActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                msg = "Settings";
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
