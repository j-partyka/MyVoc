package com.learnitgirl.myvoc.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.utils.Word;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joanna on 14.07.16.
 */
public class WordListViewAdapter extends ArrayAdapter<Word> {
    static class ViewHolder {
        @Bind(R.id.foreignWordTextView)
        TextView foreignWordTextView;
        @Bind(R.id.nativeWordTextView)
        TextView nativeWordTextView;
//        @Bind(R.id.knowledgeTextView)
//        TextView knowledgeTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private final Context context;
    private final List<Word> wordList;

    public WordListViewAdapter(Context context, List<Word> list) {
        super(context, R.layout.row, list);
        this.context = context;
        this.wordList = list;
    }

    @Override
    public Word getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public int getPosition(Word item) {
        return super.getPosition(item);
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.row, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.foreignWordTextView.setText(wordList.get(position).getForeignWord());
        holder.nativeWordTextView.setText(wordList.get(position).getNativeWord());
//        holder.knowledgeTextView.setText(wordList.get(position).getKnowledge());

        return view;
    }
}
