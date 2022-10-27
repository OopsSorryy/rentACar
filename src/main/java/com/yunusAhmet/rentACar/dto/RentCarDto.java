package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentCarDto {

    private int rentalId;

    private LocalDateTime rentDate;

    private LocalDateTime returnDate;

    private CustomerDto customer;

    private CarDto car;


}
