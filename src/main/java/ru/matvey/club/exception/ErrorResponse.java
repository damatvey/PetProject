package ru.matvey.club.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//потому что это по сути dto
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    //два обязательных поля
}
