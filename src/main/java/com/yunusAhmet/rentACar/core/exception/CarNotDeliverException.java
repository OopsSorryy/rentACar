package com.yunusAhmet.rentACar.core.exception;

public class CarNotDeliverException extends RuntimeException {
    public CarNotDeliverException(String message) {
        super(message);
    }
}