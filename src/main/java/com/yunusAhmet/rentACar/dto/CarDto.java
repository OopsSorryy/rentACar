package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int carId;

    @Size(min = 2,message = "Car Name must be bigger than 2")
    private String carName;

    @Min(value = 0,message = "Daily Price of car must be bigger than 0")
    private Long dailyPrice;

    private String productYear;

    private BrandDto brand;

    private List<ColorDto> carColors;


}
