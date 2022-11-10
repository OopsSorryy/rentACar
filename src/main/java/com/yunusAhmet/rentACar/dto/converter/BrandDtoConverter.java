package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.BrandDto;
import com.yunusAhmet.rentACar.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandDtoConverter {

    public BrandDto convert(Brand from){
        return new BrandDto
                (
                from.getBrandId(),
                from.getBrandName()
                );
    }
}
