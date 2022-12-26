package com.yunusahmet.rentacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.yunusahmet.rentacar.core.validator.create.PasswordMatches;
import com.yunusahmet.rentacar.core.validator.ValidPassword;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class CreateCustomerRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

}
