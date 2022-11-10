package com.yunusAhmet.rentACar.core.exception;




public class CustomerEmailAlreadyExistException extends RuntimeException {
    public CustomerEmailAlreadyExistException(String message) {
        super(message);
    }
}
