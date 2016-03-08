package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.DictionaryDBHelper;

public class LearnActivity extends AppCompatActivity {

    DictionaryDBHelper dbHelper = new DictionaryDBHelper(this);
    TextView shownWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         shownWord= (TextView) findViewById(R.id.shownWordTextView);

        shownWord.setText(dbHelper.getForeignWordById("0"));

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

        Toast.makeText(this, msg + " clicked!", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    public void submitWord(View w){


        EditText guessWord = (EditText) findViewById(R.id.guessWordEditText);



        if(dbHelper.getNativeWord(shownWord.getText().toString()) == guessWord.getText().toString()){


        }


    }
}
