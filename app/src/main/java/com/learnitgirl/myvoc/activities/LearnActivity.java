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
import com.learnitgirl.myvoc.database.DictionaryDatabase;

public class LearnActivity extends AppCompatActivity {

    private static final String TAG = "LearnActivity";
    DictionaryDatabase db;
    TextView shownWord;
    Button submitBtn;
    EditText guessWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DictionaryDatabase(this);

        db.open();

        shownWord = (TextView) findViewById(R.id.shownWordTextView);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        guessWord = (EditText) findViewById(R.id.guessWordEditText);

        String foreignWord = db.getRandomForeignWord();
        shownWord.setText(foreignWord);
        db.close();
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

    public void submitWord(View w) {
        db.open();

        if (db.checkForeignWord(shownWord.getText().toString(), guessWord.getText().toString())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            guessWord.setText("");
            shownWord.setText(db.getRandomForeignWord());

        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
            guessWord.setText("");
        }
        db.close();
    }
}
