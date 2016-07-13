package com.learnitgirl.myvoc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.Repo;
import com.learnitgirl.myvoc.utils.Word;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWordFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "0";
    private static final String TAG = NewWordFragment.class.getSimpleName();
    Button saveBtn;
    private Repo repo;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_word, container, false);
        repo = new Repo(getActivity());
        saveBtn = (Button) view.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText foreignEditText = (EditText) view.findViewById(R.id.foreignWordEditText);
                EditText nativeEditText = (EditText) view.findViewById(R.id.nativeWordEditText);

                String foreignWord = foreignEditText.getText().toString();
                String nativeWord = nativeEditText.getText().toString();

                Word word = new Word("1", foreignWord, nativeWord, 0);
//                BasicUser basicUser = new BasicUser("1324", firstName, lastName);

                if (repo.Words.getByForeign(word.getForeignWord()) != null){
                    Toast.makeText(getContext(), "The word already exists in a database", Toast.LENGTH_SHORT).show();
                } else {
                        word.save(repo);
//                        EventBus.getDefault().post(new AddNewWordEvent(word));
                        Log.i(TAG, word + " added to DB");
//                    } else {
//                        Log.i(TAG, word + " cannot be added to DB");
//                    }
                }

                foreignEditText.setText("");
                nativeEditText.setText("");
            }
        });
        return view;
    }
}
