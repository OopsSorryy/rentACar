package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {

    @NotNull
    private int colorId;

    @NotBlank
    private String colorName;
}
