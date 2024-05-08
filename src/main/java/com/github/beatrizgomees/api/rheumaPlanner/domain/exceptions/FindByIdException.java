package com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions;

public class FindByIdException extends  Exception{
    public FindByIdException(String message) {
        super(message);
    }

    public FindByIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
