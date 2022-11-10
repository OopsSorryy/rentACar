package com.yunusAhmet.rentACar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RentACarRequest {

    @NotNull
    private LocalDateTime returnDate;

    @NotNull
    private int customerId;

    @NotNull
    private int carId;
}
