package com.yunusahmet.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Car;

import java.util.List;

public interface CarDao extends JpaRepository<Car,Integer> {

    List<Car> getCarsByBrand_BrandId(int brandId);

}
