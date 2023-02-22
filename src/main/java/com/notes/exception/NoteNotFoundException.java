package com.notes.exception;

/**
 * Notes related exception class
 */
public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(final String message) {
        super(message);
    }
}
