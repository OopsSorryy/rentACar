package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RentACarRequest {

    @NotBlank
    private Date returnDate;

    @NotNull
    private int customerId;

    @NotNull
    private int carId;
}