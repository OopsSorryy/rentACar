package com.yunusAhmet.rentACar.dto;

import com.yunusAhmet.rentACar.entity.Car;
import com.yunusAhmet.rentACar.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Date;


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
