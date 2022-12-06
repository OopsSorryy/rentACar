package com.yunusahmet.rentacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

    @NotBlank
    private String colorName;
}
