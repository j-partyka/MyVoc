package com.learnitgirl.myvoc.activities;

import android.content.Intent;
import android.database.Cursor;
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
import com.learnitgirl.myvoc.database.DictionaryContract;
import com.learnitgirl.myvoc.database.DictionaryDatabase;

public class LearnActivity extends AppCompatActivity {

    private static final String TAG = "LearnActivity";
    DictionaryDatabase db;
    TextView shownWord;
    Button submitBtn;
    EditText guessWord;
    Cursor cursor;

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

        //cursor = db.getWords();
        //cursor.move(1);

        //String foreignWord = cursor.getString(cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD));

        String foreignWord = db.getRandomForeignWord();
        shownWord.setText(foreignWord);
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
        String nativeWord = cursor.getString(cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_NATIVE_WORD));

        if (guessWord.getText().toString().equals(nativeWord)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            cursor.move(1);

            guessWord.setText("");
            String foreignWord = cursor.getString(cursor.getColumnIndex(DictionaryContract.DictionaryEntry.COLUMN_NAME_FOREIGN_WORD));

            shownWord.setText(foreignWord);
        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
            guessWord.setText("");
        }
    }
}
