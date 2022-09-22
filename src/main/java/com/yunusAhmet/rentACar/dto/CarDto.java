package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CarDto {

    //carDto ıle car entıty ıcerısındekı degısken ısımlerının aynı olması lazım

    private int carId;

    @Size(min = 2,message = "Car Name must be bigger than 2")
    private String carName;

    @Min(value = 0,message = "Daily Price of car must be bigger than 0")
    private Long dailyPrice;

    private String productYear;

    private BrandDto brand;

    private List<ColorDto> carColors;


}
