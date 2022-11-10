package com.yunusAhmet.rentACar.dto;

import com.yunusAhmet.rentACar.core.validator.PasswordMatches;
import com.yunusAhmet.rentACar.core.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@PasswordMatches(message ="Password dont match")
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
