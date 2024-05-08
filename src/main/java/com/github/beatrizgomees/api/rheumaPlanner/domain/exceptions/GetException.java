package com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions;

public class GetException extends RuntimeException{
    public GetException(String message) {
        super(message);
    }

    public GetException(String message, Throwable cause) {
        super(message, cause);
    }
}