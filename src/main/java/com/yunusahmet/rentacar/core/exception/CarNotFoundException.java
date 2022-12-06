package com.yunusahmet.rentacar.core.exception;


public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }
}
