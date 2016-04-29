package com.learnitgirl.myvoc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DictionaryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "1";
    ListView listView;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DictionaryFragment newInstance(int sectionNumber) {
       DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        listView = (ListView) view.findViewById(R.id.dictionary_listview);

        listView.setAdapter(MainActivity.dbHelper.getWordsAdapter(getActivity()));

        registerForContextMenu(listView);
        // Inflate the layout for this fragment
        return view;
    }



}
