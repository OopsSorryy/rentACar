package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color,Integer> {


}
