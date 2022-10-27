package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.CustomerDto;
import com.yunusAhmet.rentACar.entity.Customer;
import org.springframework.stereotype.Component;

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
