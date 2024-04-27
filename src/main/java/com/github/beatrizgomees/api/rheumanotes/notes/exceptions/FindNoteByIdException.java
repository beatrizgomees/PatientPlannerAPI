package com.github.beatrizgomees.api.rheumanotes.notes.exceptions;

public class FindNoteByIdException extends  Exception{
    public FindNoteByIdException(String message) {
        super(message);
    }

    public FindNoteByIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
