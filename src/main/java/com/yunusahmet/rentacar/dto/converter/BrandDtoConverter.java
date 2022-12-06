package com.yunusahmet.rentacar.dto.converter;

import com.yunusahmet.rentacar.dto.BrandDto;
import com.yunusahmet.rentacar.entity.Brand;
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
