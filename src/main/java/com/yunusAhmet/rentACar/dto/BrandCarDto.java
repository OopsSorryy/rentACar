package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandCarDto {

    private int carId;

    private String carName;

    private Long dailyPrice;

    private String productYear;

    private List<ColorDto> carColors;

    public BrandCarDto(int carId, String carName) {
        this.carId = carId;
        this.carName = carName;
    }

}
