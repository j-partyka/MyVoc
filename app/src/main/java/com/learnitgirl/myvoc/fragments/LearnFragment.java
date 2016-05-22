package com.learnitgirl.myvoc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "2";
    TextView shownWord;
    Button submitBtn;
    EditText guessWord;

    public LearnFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LearnFragment newInstance(int sectionNumber) {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        shownWord = (TextView) view.findViewById(R.id.shownWordTextView);
        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        guessWord = (EditText) view.findViewById(R.id.guessWordEditText);

        String foreignWord = MainActivity.dbHelper.getWordString(1);
        shownWord.setText(foreignWord);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dbHelper.checkForeignWord(shownWord.getText().toString(), guessWord.getText().toString())) {
                    Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    MainActivity.dbHelper.increaseKnowledge(shownWord.getText().toString());
                    guessWord.setText("");
                    shownWord.setText(MainActivity.dbHelper.getTheMostUnknownWord());

                } else {
                    Toast.makeText(getContext(), "Try again!", Toast.LENGTH_SHORT).show();
                    guessWord.setText("");
                }
            }
        });
        return view;
    }
}
