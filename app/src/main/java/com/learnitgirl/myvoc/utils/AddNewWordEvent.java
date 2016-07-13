package com.learnitgirl.myvoc.utils;

/**
 * Created by joanna on 04.05.16.
 */
public class AddNewWordEvent {
    public final Word word;

    public AddNewWordEvent(Word word){
        this.word = word;
    }

    public Word getWord() {
        return word;
    }
}
