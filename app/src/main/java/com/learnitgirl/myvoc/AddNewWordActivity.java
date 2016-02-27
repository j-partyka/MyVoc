package com.learnitgirl.myvoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.learnitgirl.myvoc.database.DictionaryDBHelper;

public class AddNewWordActivity extends AppCompatActivity {

    DictionaryDBHelper dbHelper = new DictionaryDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add New Word");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String msg = "";

        switch (item.getItemId()) {
            case R.id.action_learn:
                msg = "Learn";
                break;
            case R.id.action_new_word:
                msg = "New Word";
                break;
            case R.id.action_dictionary:
                msg = "Dictionary";
                break;
            case R.id.action_settings:
                msg = "Settings";
                break;
        }

        Toast.makeText(this, msg + " clicked!", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    public void save(View v) {
        EditText foreignEditText = (EditText) findViewById(R.id.foreignWordEditText);

        EditText nativeEditText = (EditText) findViewById(R.id.nativeWordEditText);

        String foreignWord = String.valueOf(foreignEditText.getText());
        String nativeWord = String.valueOf(nativeEditText.getText());

        if (foreignWord.equals(dbHelper.getForeignWord(nativeWord)) && nativeWord.equals(dbHelper.getNativeWord(foreignWord))) {
            Toast.makeText(this, "The word already exists in a database", Toast.LENGTH_SHORT).show();
        } else {
            if (dbHelper.insert(foreignWord, nativeWord, "NEW")) {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Not saved!", Toast.LENGTH_SHORT).show();
            }
        }

        foreignEditText.setText("");
        nativeEditText.setText("");
    }
}
