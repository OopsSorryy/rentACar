package com.yunusAhmet.rentACar.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ColorAlreadyExistException extends RuntimeException {

    public ColorAlreadyExistException(String message) {
        super(message);
    }
}
