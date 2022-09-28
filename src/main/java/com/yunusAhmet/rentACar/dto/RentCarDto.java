package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentCarDto {

    private int rentalId;

    private Date rentDate;

    private Date returnDate;

    private CustomerDto customer;

    private CarDto car;

}
