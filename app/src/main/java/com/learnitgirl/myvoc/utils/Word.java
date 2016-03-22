package com.learnitgirl.myvoc.utils;

/**
 * Created by joanna on 22.03.16.
 */
public class Word {
    private String nativeWord;
    private String foreignWord;
    private int knowledge = 0;

    public Word(String nativeWord, String foreignWord, int knowledge) {
        this.nativeWord = nativeWord;
        this.foreignWord = foreignWord;
        this.knowledge = knowledge;
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

}
