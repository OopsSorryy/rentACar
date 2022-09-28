package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.BrandAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.BrandNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.BrandDao;
import com.yunusAhmet.rentACar.dto.BrandCarDto;
import com.yunusAhmet.rentACar.entity.Brand;
import com.yunusAhmet.rentACar.entity.Car;
import com.yunusAhmet.rentACar.dto.BrandDto;
import com.yunusAhmet.rentACar.dto.CreateBrandRequest;
import com.yunusAhmet.rentACar.dto.UpdateBrandRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    protected void findTheSameBrand(CreateBrandRequest request) {
        Optional<Brand> brand = brandDao.findBrandByBrandName(request.getBrandName());
        if (brand.isPresent()) {
            throw new BrandAlreadyExistException(Constant.BRAND_ALREADY_EXIST);
        }
    }

    public BrandDto createBrand(CreateBrandRequest request){
        findTheSameBrand(request);
        Brand brand = new Brand(request.getBrandName());
        return modelMapper.map(brandDao.save(brand),BrandDto.class);
    }

    public void deleteBrandByBrandId(int brandId){
        brandDao.deleteById(getBrandByBrandId(brandId).getBrandId());
    }

    public BrandDto updateBrand(UpdateBrandRequest request){
        Brand brand = getBrandByBrandId(request.getBrandId());
        brand.setBrandName(request.getBrandName());
        return modelMapper.map(brandDao.save(brand),BrandDto.class);
    }


    public List<BrandCarDto> getAllCarByBrandId(int brandId) {
        Brand brand =getBrandByBrandId(brandId);

        List<Car> cars= brand.getCar();

        return cars
                .stream()
                .map(car -> modelMapper
                        .map(car, BrandCarDto.class)).toList();

    }



}
