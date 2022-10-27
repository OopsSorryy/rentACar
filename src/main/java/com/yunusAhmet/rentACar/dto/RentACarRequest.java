package com.yunusAhmet.rentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentACarRequest {

    @NotNull
    private LocalDateTime returnDate;

    @NotNull
    private int customerId;

    @NotNull
    private int carId;
}