package com.learnitgirl.myvoc.utils;

/**
 * Created by joanna on 04.05.16.
 */
public class NewWordAddedEvent {
    public final String message;

    public NewWordAddedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
