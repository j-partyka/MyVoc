package com.learnitgirl.myvoc.database;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.learnitgirl.myvoc.utils.Word;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by joanna on 13.07.16.
 */
public class RepoWords {
    private Dao<Word, String> wordDao;
    private static final String TAG = RepoWords.class.getCanonicalName();

    public RepoWords(DatabaseHelper db)
    {
        try {
            wordDao = db.getWordDao();
        } catch (SQLException e) {
            Log.e(TAG, "Cannot get WordDAO");
            e.printStackTrace();
        }
    }

    public int create(Word word)
    {
        try {
            return wordDao.create(word);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot create word");
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Word word)
    {
        try {
            return wordDao.update(word);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot update word");
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Word word)
    {
        try {
            return wordDao.delete(word);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot delete word");
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(String id)
    {
        try {
            return wordDao.deleteById(id);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot delete word");
            e.printStackTrace();
        }
        return 0;
    }
    public Word getByForeign(String foreignWord)
    {
        try {
            QueryBuilder<Word, String> qb = wordDao.queryBuilder();

            qb.where().eq("foreignWord", foreignWord);

            PreparedQuery<Word> pq = qb.prepare();
            return wordDao.queryForFirst(pq);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot get word by foreign");
            e.printStackTrace();
        }
        return null;
    }

    public Word getByNative(String nativeWord)
    {
        try {
            QueryBuilder<Word, String> qb = wordDao.queryBuilder();

            qb.where().eq("nativeWord", nativeWord);

            PreparedQuery<Word> pq = qb.prepare();
            return wordDao.queryForFirst(pq);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot get word by native");
            e.printStackTrace();
        }
        return null;
    }

    public Word getByID(String id)
    {
        try {
            QueryBuilder<Word, String> qb = wordDao.queryBuilder();

            qb.where().eq("id", id);

            PreparedQuery<Word> pq = qb.prepare();
            return wordDao.queryForFirst(pq);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot get word by ID");
            e.printStackTrace();
        }
        return null;
    }
    public List<Word> getAll()
    {
        try {
            return wordDao.queryForAll();
        } catch (SQLException e) {
            Log.e(TAG, "Cannot get all words");
            e.printStackTrace();
        }
        return null;
    }
}

