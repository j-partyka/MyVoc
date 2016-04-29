package com.learnitgirl.myvoc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.activities.MainActivity;
import com.learnitgirl.myvoc.utils.Word;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWordFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "0";

    public NewWordFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
        public static NewWordFragment newInstance(int sectionNumber) {
        NewWordFragment fragment = new NewWordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_word, container, false);
    }

    public void save(View v) {
        EditText foreignEditText = (EditText) getActivity().findViewById(R.id.foreignWordEditText);

        EditText nativeEditText = (EditText) getActivity().findViewById(R.id.nativeWordEditText);

        String foreignWord = foreignEditText.getText().toString();
        String nativeWord = nativeEditText.getText().toString();

        Word word = new Word(foreignWord, nativeWord, 0);

        if (foreignWord.equals(MainActivity.dbHelper.getForeignWord(nativeWord)) && nativeWord.equals(MainActivity.dbHelper.getNativeWord(foreignWord))) {
            Toast.makeText(this.getActivity(), "The word already exists in a database", Toast.LENGTH_SHORT).show();
        } else {
            if (MainActivity.dbHelper.insertWord(word)) {
                Toast.makeText(this.getActivity(), "Saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getActivity(), "Not saved!", Toast.LENGTH_SHORT).show();
            }
        }

        foreignEditText.setText("");
        nativeEditText.setText("");
    }

}
