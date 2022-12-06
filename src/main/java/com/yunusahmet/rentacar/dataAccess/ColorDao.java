package com.yunusahmet.rentacar.dataAccess;

import com.yunusahmet.rentacar.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColorDao extends JpaRepository<Color,Integer> {
    Optional<Color> findColorByColorName(String colorName);
    Optional<List<Color>> findColorsByColorIdIn(List<Integer> colorId);

}
