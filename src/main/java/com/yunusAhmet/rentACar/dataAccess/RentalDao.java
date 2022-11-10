package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalDao extends JpaRepository<Rental,Integer> {

}
