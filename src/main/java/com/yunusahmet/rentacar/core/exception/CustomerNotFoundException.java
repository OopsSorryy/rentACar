package com.yunusahmet.rentacar.core.exception;




public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
