package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.BrandNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.BrandDao;
import com.yunusAhmet.rentACar.dto.BrandCarDto;
import com.yunusAhmet.rentACar.entity.Brand;
import com.yunusAhmet.rentACar.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager {

    private final BrandDao brandDao;
    private final ModelMapper modelMapper;


    public BrandManager(BrandDao brandDao, ModelMapper modelMapper) {
        this.brandDao = brandDao;
        this.modelMapper = modelMapper;
    }

    protected Brand getBrandByBrandId(int brandId){
       return brandDao.findById(brandId).orElseThrow(() -> new BrandNotFoundException(Constant.BRAND_NOT_FOUND));
    }

    public List<BrandCarDto> getAllCarByBrandId(int brandId) {
        Brand brand = getBrandByBrandId(brandId);

        List<Car> cars = brand.getCar();

        List<BrandCarDto> dtos= cars
                .stream()
                .map(car -> modelMapper
                        .map(car, BrandCarDto.class)).toList();
        return dtos;
    }


}
