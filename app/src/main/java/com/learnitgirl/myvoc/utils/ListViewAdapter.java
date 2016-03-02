package com.learnitgirl.myvoc.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.learnitgirl.myvoc.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by joanna on 02.03.16.
 */
public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView textFirst;
    TextView textSecond;
    TextView textThird;

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(list.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if(convertView == null){
            convertView = inflater.inflate(R.layout.column_row,null);

            textFirst = (TextView) convertView.findViewById(R.id.foreignWordTextView);
            textSecond = (TextView) convertView.findViewById(R.id.nativeWordTextView);
            textThird = (TextView) convertView.findViewById(R.id.knowledgeTextView);
        }

        HashMap<String, String> map = list.get(position);
        textFirst.setText(map.get(Constants.FIRST_COLUMN));
        textSecond.setText(map.get(Constants.SECOND_COLUMN));
        textThird.setText(map.get(Constants.THIRD_COLUMN));

        return convertView;
    }
}
