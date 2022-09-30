package com.yunusAhmet.rentACar.core.exception;




public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
