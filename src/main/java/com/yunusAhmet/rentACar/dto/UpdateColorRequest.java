package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateColorRequest {

    @NotNull
    private int colorId;

    @NotBlank
    private String colorName;
}
