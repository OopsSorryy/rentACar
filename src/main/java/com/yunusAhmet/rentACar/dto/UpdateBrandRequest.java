package com.yunusAhmet.rentACar.dto;

import lombok.Data;

@Data
public class UpdateBrandRequest {

    private int brandId;
    private String brandName;
}
