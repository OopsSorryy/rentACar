package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Brand;
import com.yunusAhmet.rentACar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandDao extends JpaRepository<Brand,Integer> {
   Optional<Brand> findBrandByBrandName(String brandName);



}
