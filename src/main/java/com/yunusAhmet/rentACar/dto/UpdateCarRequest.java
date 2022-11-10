package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateCarRequest {

    @NotNull
    private int carId;

    @NotBlank
    @Size(min = 3,message = "Car Name must be bigger than 2")
    private String carName;

    @NotNull
    @Min(value = 0,message = "Daily Price of car must be bigger than 0")
    private Long dailyPrice;

    @NotBlank
    private String productYear;

    @NotNull
    private int brandId;

    @NotNull
    private List<Integer> colorId;

}
