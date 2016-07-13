package com.learnitgirl.myvoc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.learnitgirl.myvoc.R;
import com.learnitgirl.myvoc.database.Repo;
import com.learnitgirl.myvoc.utils.Word;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DictionaryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "1";
    private static final String TAG = DictionaryFragment.class.getSimpleName();

    Repo repo;
    private ArrayList<Word> words;

    WordListViewAdapter adapter;

    @Bind(R.id.dictionary_listview)
    ListView listView;

//    private ListView listView;
//        private SimpleCursorAdapter adapter;
//    private ArrayAdapter adapter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
        repo = new Repo(getActivity());
        words = new ArrayList<>(4);

//        adapter = MainActivity.dbHelper.getWordsAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        ButterKnife.bind(this, view);

//        listView.setAdapter(adapter);
        if (repo.Words.getAll() != null) {
            words.addAll(repo.Words.getAll());
        }
//        words = new ArrayList<>(repo.Words.getAll());
//        updateWordList();
        adapter = new WordListViewAdapter(getActivity(), words);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        return view;
    }

    private void updateWordList() {
        words.add(new Word("1", "Marcin", "Skowron", 0));
        words.add(new Word("2", "Marcin2", "Skowron2", 0));
        words.add(new Word("3", "Marcin3", "Skowron3", 0));
        words.add(new Word("4", "Marcin4", "Skowron4", 0));
        words.add(new Word("5", "Marcin5", "Skowron5", 0));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
//            case R.id.edit_word:
//                Toast.makeText(getContext(), "Edit clicked ", Toast.LENGTH_SHORT).show();
//                //TODO: create dialog for edits
//                //edit_word(info.id);
//                return true;
            case R.id.delete_word:
                repo.Words.delete(String.valueOf(info.id));
//                MainActivity.dbHelper.deleteWord(String.valueOf(info.id));
//                adapter.swapCursor(MainActivity.dbHelper.getWords());
//                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            default:
                return super.onContextItemSelected(item);
        }
    }

//    @Subscribe
//    public void onEvent(AddNewWordEvent event) {
////        Toast.makeText(DictionaryFragment.this.getContext(), event.getWord() + "has been delivered in Dictionary", Toast.LENGTH_SHORT).show();
////        adapter.changeCursor(newCursor);
////        adapter.notifyDataSetChanged();
//        Context context = getContext() != null ? getContext() : (getActivity() != null ? getActivity() : null);
//        if (context != null) {
//            Log.i(TAG, event.getWord().toString() + " start processing event - update the view.");
//            Cursor newCursor = MainActivity.dbHelper.insertWordReturnCursor(event.getWord());
//            listView.setAdapter(MainActivity.dbHelper.getWordsAdapter(context));
//        } else {
//            Log.i(TAG, "There is no context!");
//        }
//        Log.i(TAG, event.getWord() + " Saved and ListView updated");
//    }
}
