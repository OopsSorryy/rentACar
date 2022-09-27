package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BrandCarDto {

    private int carId;

    private String carName;

    private Long dailyPrice;

    private String productYear;

    private List<ColorDto> carColors;

}
