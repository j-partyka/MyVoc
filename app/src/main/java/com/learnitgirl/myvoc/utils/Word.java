package com.learnitgirl.myvoc.utils;

import android.content.ContentValues;
import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.learnitgirl.myvoc.database.Repo;

@DatabaseTable(tableName = "dictionary")
public class Word {
    private static final String TAG = Word.class.getSimpleName();
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String foreignWord;
    @DatabaseField
    private String nativeWord;
    private int knowledge = 0;

    public Word(String foreignWord, String nativeWord, int knowledge) {
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
        this.knowledge = knowledge;
    }

    public Word(String id, String foreignWord, String nativeWord, int knowledge) {
        this.id = id;
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
        this.knowledge = knowledge;
    }

    public Word() {
    }

    public String getNativeWord() {
        return nativeWord;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setNativeWord(String nativeWord) {
        this.nativeWord = nativeWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public int save(Repo repo) {
        if (repo.Words.getByForeign(foreignWord) == null) {
            Log.d(TAG, this + " created");
            return repo.Words.create(this);
        } else {
            return repo.Words.update(this);
        }
    }

    public int delete(Repo repo) {
        return repo.Words.delete(this);
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("foreignWord", foreignWord);
        values.put("nativeWord", nativeWord);
        values.put("knowledge", knowledge);
        return values;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", foreignWord='" + foreignWord + '\'' +
                ", nativeWord='" + nativeWord + '\'' +
                ", knowledge=" + knowledge +
                '}';
    }
}
