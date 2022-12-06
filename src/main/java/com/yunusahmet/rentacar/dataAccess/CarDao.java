package com.yunusahmet.rentacar.dataAccess;

import com.yunusahmet.rentacar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarDao extends JpaRepository<Car,Integer> {

    Optional<List<Car>> getCarsByBrand_BrandId(int brandId);

}
