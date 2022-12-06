package com.yunusahmet.rentacar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageDao extends JpaRepository<Image,Integer> {
    Optional<List<Image>> getImagesByCar_CarId(int carId);
}
