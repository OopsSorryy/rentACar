package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.RentCarDto;
import com.yunusAhmet.rentACar.entity.Rental;
import org.springframework.stereotype.Component;

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
