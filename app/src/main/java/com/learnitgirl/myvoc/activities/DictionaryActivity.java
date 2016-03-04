package com.learnitgirl.myvoc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.utils.Constants;
import com.learnitgirl.myvoc.utils.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_activity_dictionary);

        ListView listView = (ListView) findViewById(R.id.dictionary_listview);
        list = new ArrayList<>();

        HashMap<String, String> temp1 = new HashMap<>();
        HashMap<String, String> temp2 = new HashMap<>();
        HashMap<String, String> temp3 = new HashMap<>();

        temp1.put(Constants.FIRST_COLUMN, "door");
        temp1.put(Constants.SECOND_COLUMN, "drzwi");
        temp1.put(Constants.THIRD_COLUMN, "new");
        list.add(temp1);

        temp2.put(Constants.FIRST_COLUMN, "door2");
        temp2.put(Constants.SECOND_COLUMN, "drzwi2");
        temp2.put(Constants.THIRD_COLUMN, "new2");
        list.add(temp2);

        temp3.put(Constants.FIRST_COLUMN, "door3");
        temp3.put(Constants.SECOND_COLUMN, "drzwi3");
        temp3.put(Constants.THIRD_COLUMN, "new3");
        list.add(temp3);

        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DictionaryActivity.this, position+1 + "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
