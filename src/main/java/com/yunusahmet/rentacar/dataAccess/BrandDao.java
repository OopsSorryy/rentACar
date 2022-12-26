package com.yunusahmet.rentacar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Brand;

import java.util.Optional;

public interface BrandDao extends JpaRepository<Brand,Integer> {
   Optional<Brand> findBrandByBrandName(String brandName);





}
