package com.yunusAhmet.rentACar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;

    private String carName;

    private Long dailyPrice;

    private String productYear;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "car_color",
            joinColumns = @JoinColumn(name = "carId"),
            inverseJoinColumns = @JoinColumn(name = "colorId"))
    List<Color> carColors;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    private Brand brand;

    public Car( String carName, Long dailyPrice, String productYear, Brand brand,List<Color> carColors) {
        this.carName = carName;
        this.dailyPrice = dailyPrice;
        this.productYear = productYear;
        this.brand = brand;
        this.carColors = carColors;
    }
}
