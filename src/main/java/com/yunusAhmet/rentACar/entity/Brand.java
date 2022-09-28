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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    private String brandName;


    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "brand",fetch = FetchType.LAZY)
    private List<Car> car;

    public Brand(String brandName){
        this.brandName = brandName;
    }
}
