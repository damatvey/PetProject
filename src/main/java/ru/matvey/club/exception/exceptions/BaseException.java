package ru.matvey.club.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public class BaseException extends RuntimeException{
    //то, что можем выбросить в процессе рантайма
    private final HttpStatus status;

    public BaseException(HttpStatus status, String message) {
        //посмотреть как у Миши в репозитории
        super(message);
        this.status = status;
    }
}
