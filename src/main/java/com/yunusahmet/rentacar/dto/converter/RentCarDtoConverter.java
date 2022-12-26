package com.yunusahmet.rentacar.dto.converter;

import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.RentCarDto;
import com.yunusahmet.rentacar.entity.Rental;

@Component
public class RentCarDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final CarDtoConverter carDtoConverter;

    public RentCarDtoConverter(CustomerDtoConverter customerDtoConverter, CarDtoConverter carDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.carDtoConverter = carDtoConverter;
    }

    public RentCarDto convert(Rental from){
        return new RentCarDto(
                from.getRentalId(),
                from.getRentDate(),
                from.getReturnDate(),
                customerDtoConverter.convert(from.getCustomer()),
                carDtoConverter.convert(from.getCar())
        );

    }
}
