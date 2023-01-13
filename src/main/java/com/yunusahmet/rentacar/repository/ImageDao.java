package com.yunusahmet.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Image;

import java.util.List;

public interface ImageDao extends JpaRepository<Image,Integer> {
    List<Image> getImagesByCar_CarId(int carId);
}
