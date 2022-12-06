package com.yunusahmet.rentacar.core.exception;


public class CarNotDeliverException extends RuntimeException {
    public CarNotDeliverException(String message) {
        super(message);
    }
}
