package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car,Integer> {

}
