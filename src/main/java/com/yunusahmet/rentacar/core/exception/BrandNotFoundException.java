package com.yunusahmet.rentacar.core.exception;


public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String message) {
        super(message);
    }
}
