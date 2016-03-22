package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.DictionaryDatabase;
import com.learnitgirl.myvoc.utils.Word;

public class AddNewWordActivity extends AppCompatActivity {

    DictionaryDatabase db = new DictionaryDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_activity_add_new_word);

        db.open();
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

    public void save(View v) {
        EditText foreignEditText = (EditText) findViewById(R.id.foreignWordEditText);

        EditText nativeEditText = (EditText) findViewById(R.id.nativeWordEditText);

        String foreignWord = String.valueOf(foreignEditText.getText());
        String nativeWord = String.valueOf(nativeEditText.getText());

        Word word = new Word(foreignWord, nativeWord, 0);

        if (foreignWord.equals(db.getForeignWord(nativeWord)) && nativeWord.equals(db.getNativeWord(foreignWord))) {
            Toast.makeText(this, "The word already exists in a database", Toast.LENGTH_SHORT).show();
        } else {
            if (db.insertWord(word)) {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Not saved!", Toast.LENGTH_SHORT).show();
            }
        }

        foreignEditText.setText("");
        nativeEditText.setText("");
    }
}
