package com.yunusahmet.rentacar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CarDto {

    //carDto ıle car entıty ıcerısındekı degısken ısımlerının aynı olması lazım

    private int carId;

    @Size(min = 2,message = "Car Name must be bigger than 2")
    private String carName;

    @Min(value = 0,message = "Daily Price of car must be bigger than 0")
    private Long dailyPrice;

    private String productYear;

    private BrandDto brand;

    private List<ImageDto> image;

    private List<ColorDto> carColors;


}
