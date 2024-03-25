package ru.matvey.club.exception.exception;

import org.springframework.http.HttpStatus;

public class WeakPasswordException extends BaseException{
    public WeakPasswordException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
