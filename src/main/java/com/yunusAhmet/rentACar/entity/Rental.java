package com.yunusAhmet.rentACar.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;//UUID

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

    public Rental(int rentalId,LocalDateTime rentDate, LocalDateTime returnDate, Customer customer, Car car) {
        this.rentalId = rentalId;
        this.returnDate = returnDate;
        this.rentDate = rentDate;
        this.customer = customer;
        this.car = car;
    }

    //annotation ıle unıq olup olmadıgını bellı et

}
