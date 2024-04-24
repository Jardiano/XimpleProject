package com.example.ximpleproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not found")
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
