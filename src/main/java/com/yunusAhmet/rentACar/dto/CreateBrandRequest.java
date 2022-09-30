package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBrandRequest {

    @NotBlank
    private String brandName;
}
