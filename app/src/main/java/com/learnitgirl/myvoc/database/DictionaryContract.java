package com.learnitgirl.myvoc.database;

import android.provider.BaseColumns;

public final class DictionaryContract {

    public DictionaryContract() {
    }

    public static abstract class DictionaryEntry implements BaseColumns {
        public static final String TABLE_NAME = "dictionary";
        public static final String COLUMN_NAME_FOREIGN_WORD = "foreignWord";
        public static final String COLUMN_NAME_NATIVE_WORD = "nativeWord";
        public static final String COLUMN_NAME_KNOWLEDGE = "knowledge";

    }
}
