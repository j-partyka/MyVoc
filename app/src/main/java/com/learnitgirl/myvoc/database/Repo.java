package com.learnitgirl.myvoc.database;

/**
 * Created by joanna on 13.07.16.
 */

import android.content.Context;

/**
 * @link follow this tutorial: http://www.b-fil.com/blog/2011/01/20/android-repository-ormlite-existing-sqlite-db
 */

public class Repo {

    DatabaseHelper db;

    public RepoWords Words;

    public Repo(Context context)
    {
        if(db == null) {
            db = new DatabaseHelper(context);
        }
        Words = new RepoWords(db);
    }
}