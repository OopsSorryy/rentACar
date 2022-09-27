package com.yunusAhmet.rentACar.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateCarRequest {


    @Size(min = 2,message = "Car Name must be bigger than 2")
    private String carName;

    @Min(value = 0,message = "Daily Price of car must be bigger than 0")
    private Long dailyPrice;

    private String productYear;

    private int brandId;

    private List<Integer> colorId;




}
