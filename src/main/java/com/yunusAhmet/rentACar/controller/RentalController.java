package com.yunusAhmet.rentACar.controller;


import com.yunusAhmet.rentACar.business.RentalManager;
import com.yunusAhmet.rentACar.dto.RentACarRequest;
import com.yunusAhmet.rentACar.dto.RentCarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/rental")
@RestController
public class RentalController {

    private final RentalManager rentalManager;

    public RentalController(RentalManager rentalManager) {
        this.rentalManager = rentalManager;
    }

    @PostMapping
    public ResponseEntity<RentCarDto> rentCar(@Valid @RequestBody RentACarRequest request){
        return new ResponseEntity<>(rentalManager.rentACar(request), HttpStatus.CREATED);
    }
}
