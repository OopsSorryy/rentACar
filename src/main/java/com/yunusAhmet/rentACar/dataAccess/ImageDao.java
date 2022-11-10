package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image,Integer> {
}
