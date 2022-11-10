package com.yunusAhmet.rentACar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerDto {

    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

}
