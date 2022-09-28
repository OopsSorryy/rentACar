package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentACarRequest {

    private Date returnDate;

    private int customerId;

    private int carId;
}