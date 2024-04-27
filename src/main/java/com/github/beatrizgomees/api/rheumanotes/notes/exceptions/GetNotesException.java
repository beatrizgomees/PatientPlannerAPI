package com.github.beatrizgomees.api.rheumanotes.notes.exceptions;

public class GetNotesException extends RuntimeException{
    public GetNotesException(String message) {
        super(message);
    }

    public GetNotesException(String message, Throwable cause) {
        super(message, cause);
    }
}
