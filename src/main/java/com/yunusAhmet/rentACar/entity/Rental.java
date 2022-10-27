package com.yunusAhmet.rentACar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    private LocalDateTime rentDate  ;

    private LocalDateTime returnDate;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Car car;

    public Rental(LocalDateTime rentDate,LocalDateTime returnDate, Customer customer, Car car) {

        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.customer = customer;
        this.car = car;
    }

}
