package com.yunusahmet.rentacar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Rental;

import java.util.Optional;


public interface RentalDao extends JpaRepository<Rental,Integer> {

    Optional<Rental> getRentalByCar_CarId(int carId);
    Optional<Rental> getRentalByCustomer_CustomerId(int customerId);

}
