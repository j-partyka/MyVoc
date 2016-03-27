package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
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

        ListView listView = (ListView) findViewById(R.id.dictionary_listview);
        DictionaryDBHelper dbHelper = new DictionaryDBHelper(this);

        listView.setAdapter(dbHelper.getWordsAdapter(this));

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_learn:
                intent = new Intent(this, LearnActivity.class);
                break;
            case R.id.action_new_word:
                intent = new Intent(this, AddNewWordActivity.class);
                break;
            case R.id.action_dictionary:
                intent = new Intent(this, DictionaryActivity.class);
                break;
            case R.id.action_settings:
            default:
                intent = new Intent(this, this.getClass());
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_word:
                Toast.makeText(DictionaryActivity.this, "Edit clicked", Toast.LENGTH_SHORT).show();
                //edit_word(info.id);
                return true;
            case R.id.delete_word:
                Toast.makeText(DictionaryActivity.this, "Delete clicked", Toast.LENGTH_SHORT).show();
                //delete_word(info.id);
            default:
                return super.onContextItemSelected(item);
        }
    }
}
