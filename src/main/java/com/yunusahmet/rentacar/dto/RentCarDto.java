package com.yunusahmet.rentacar.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;



@Data

@NoArgsConstructor
@EqualsAndHashCode
public class RentCarDto {

    private int rentalId;

    private LocalDateTime rentDate;

    private LocalDateTime returnDate;

    private CustomerDto customer;

    private CarDto car;


    public RentCarDto(int rentalId, LocalDateTime rentDate,LocalDateTime returnDate, CustomerDto customer, CarDto car) {
        this.rentalId = rentalId;
        this.returnDate = returnDate;
        this.rentDate = rentDate;
        this.customer = customer;
        this.car = car;
    }
}
