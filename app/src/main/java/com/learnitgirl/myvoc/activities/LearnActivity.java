package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.DictionaryDBHelper;

public class LearnActivity extends AppCompatActivity {

    DictionaryDBHelper dbHelper;
    TextView shownWord;
    Button submitBtn;
    EditText guessWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DictionaryDBHelper(this);

        shownWord = (TextView) findViewById(R.id.shownWordTextView);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        guessWord = (EditText) findViewById(R.id.guessWordEditText);

        String foreignWord = dbHelper.getRandomForeignWord();
        shownWord.setText(foreignWord);
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

    public void submitWord(View w) {

        if (dbHelper.checkForeignWord(shownWord.getText().toString(), guessWord.getText().toString())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            guessWord.setText("");
            shownWord.setText(dbHelper.getRandomForeignWord());

        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
            guessWord.setText("");
        }
    }
}
