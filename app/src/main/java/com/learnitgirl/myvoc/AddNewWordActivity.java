package com.learnitgirl.myvoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {

//    private EditText foreignEditText = (EditText) findViewById(R.id.foreignWordEditText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add New Word");

    }

    public void disableHint(View v) {
        EditText foreignEditText = (EditText) findViewById(R.id.foreignWordEditText);

        EditText nativeEditText = (EditText) findViewById(R.id.nativeWordEditText);

        if(v == foreignEditText){
            foreignEditText.setHint("");
        }else if(v == nativeEditText);
            nativeEditText.setHint("");
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


}
