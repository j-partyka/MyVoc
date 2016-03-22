package com.learnitgirl.myvoc.utils;

/**
 * Created by joanna on 22.03.16.
 */
public class Word {
    private String nativeWord;
    private String foreignWord;
    private int knowledge;

    public String getNativeWord() {
        return nativeWord;
    }

    public String getForeign() {
        return foreignWord;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setNativeWord(String nativeWord) {
        this.nativeWord = nativeWord;
    }

    public void setForeign(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }


}
