package com.anands.virtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String exception) {
        super(exception);
    }

}