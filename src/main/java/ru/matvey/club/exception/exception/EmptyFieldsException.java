package ru.matvey.club.exception.exception;

import org.springframework.http.HttpStatus;

public class EmptyFieldsException extends BaseException{
    public EmptyFieldsException(String message) {
        //Статус не передаем, потому что знаем, что на такие ошибки
        //всегда возвращаем 400 статус
        super(HttpStatus.BAD_REQUEST, message);
    }
}
