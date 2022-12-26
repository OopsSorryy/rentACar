package com.yunusahmet.rentacar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;


import com.yunusahmet.rentacar.entity.Color;

import java.util.List;
import java.util.Optional;

public interface ColorDao extends JpaRepository<Color,Integer> {
    Optional<Color> findColorByColorName(String colorName);
    List<Color> findColorsByColorIdIn(List<Integer> colorId);

}
