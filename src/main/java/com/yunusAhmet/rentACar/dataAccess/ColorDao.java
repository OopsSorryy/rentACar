package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ColorDao extends JpaRepository<Color,Integer> {
    Optional<Color> findColorByColorName(String colorName);

}
