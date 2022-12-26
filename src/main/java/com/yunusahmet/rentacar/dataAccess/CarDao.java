package com.yunusahmet.rentacar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends JpaRepository<Car,Integer> {

    Optional<List<Car>> getCarsByBrand_BrandId(int brandId);

}
