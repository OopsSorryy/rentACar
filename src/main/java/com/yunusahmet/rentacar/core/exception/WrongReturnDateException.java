package com.yunusahmet.rentacar.core.exception;

public class WrongReturnDateException extends RuntimeException {
    public WrongReturnDateException(String message) {
        super(message);
    }
}
