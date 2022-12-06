package com.yunusahmet.rentacar.dto.converter;

import com.yunusahmet.rentacar.entity.Customer;
import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.CustomerDto;

@Component
public class CustomerDtoConverter {

    public CustomerDto convert(Customer from){
        return new CustomerDto(
                from.getCustomerId(),
                from.getFirstName(),
                from.getLastName(),
                from.getEmail()
        );
    }
}
