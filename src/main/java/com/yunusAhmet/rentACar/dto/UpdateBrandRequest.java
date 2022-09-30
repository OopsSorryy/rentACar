package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateBrandRequest {

    @NotNull
    private int brandId;

    @NotBlank
    private String brandName;
}
