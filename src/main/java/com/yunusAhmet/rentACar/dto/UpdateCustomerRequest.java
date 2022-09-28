package com.yunusAhmet.rentACar.dto;

import lombok.Data;

@Data
public class UpdateCustomerRequest {

    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
