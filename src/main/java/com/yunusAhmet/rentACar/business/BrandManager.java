package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.BrandNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.BrandDao;
import com.yunusAhmet.rentACar.entity.Brand;
import org.springframework.stereotype.Service;

@Service
public class BrandManager {

    private final BrandDao brandDao;


    public BrandManager(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    protected Brand getBrandByBrandId(int brandId){
       return brandDao.findById(brandId).orElseThrow(() -> new BrandNotFoundException(Constant.BRAND_NOT_FOUND));
    }



}
