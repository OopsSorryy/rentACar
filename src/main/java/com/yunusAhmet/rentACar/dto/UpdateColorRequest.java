package com.yunusAhmet.rentACar.dto;

import lombok.Data;

@Data
public class UpdateColorRequest {

    private int colorId;

    private String colorName;
}
