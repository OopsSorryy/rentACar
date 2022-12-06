package com.yunusahmet.rentacar.core.exception;



public class CustomerAlreadyRentACar extends RuntimeException {
    public CustomerAlreadyRentACar(String message) {
        super(message);
    }
}
