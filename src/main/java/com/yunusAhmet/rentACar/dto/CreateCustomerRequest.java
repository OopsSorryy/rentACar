package com.yunusAhmet.rentACar.dto;

import com.yunusAhmet.rentACar.core.validator.PasswordMatches;
import com.yunusAhmet.rentACar.core.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


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
