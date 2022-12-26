package com.yunusahmet.rentacar.dto;

import com.yunusahmet.rentacar.core.validator.update.UpdatePasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.yunusahmet.rentacar.core.validator.ValidPassword;

@UpdatePasswordMatches(message ="Password dont match")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateCustomerRequest {

    @NotNull
    private int customerId;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;
}
