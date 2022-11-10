package com.yunusAhmet.rentACar.core.exception;


public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }
}
