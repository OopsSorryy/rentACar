package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandDao extends JpaRepository<Brand,Integer> {



}
