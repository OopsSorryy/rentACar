package com.yunusAhmet.rentACar.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
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

    public Brand(int brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }
}
