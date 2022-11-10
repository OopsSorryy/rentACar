package com.yunusAhmet.rentACar.core.exception;



public class CustomerAlreadyRentACar extends RuntimeException {
    public CustomerAlreadyRentACar(String message) {
        super(message);
    }
}
