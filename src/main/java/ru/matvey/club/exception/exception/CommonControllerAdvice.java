package ru.matvey.club.exception.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.matvey.club.exception.ErrorResponse;

@Slf4j
//библиотека для логгирования
@ControllerAdvice
//ищет методы, которые обработались контроллером, но по какой-то причине упали
public class CommonControllerAdvice {
    //механизм Спринга
    //Определили механихзм создания ошибок
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e){
        //ОТЛАВЛИВАЕТ ВСЕ ОШИБКИ BaseException'a и делает с ними какие-то манипуляции (см. ниже)
        //ResponseEntity<ErrorResponse> - то, что вернеться вместо того, что обычно возвращается
        log.error(e.getMessage()); //логирует
        e.printStackTrace(); //печатает стэктрэйс
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().value(), e.getMessage());
        //вохвращает ответ (response - ответ)
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

}
