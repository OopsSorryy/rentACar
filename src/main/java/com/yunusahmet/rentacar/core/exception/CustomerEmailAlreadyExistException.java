package com.yunusahmet.rentacar.core.exception;




public class CustomerEmailAlreadyExistException extends RuntimeException {
    public CustomerEmailAlreadyExistException(String message) {
        super(message);
    }
}
