package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.dataAccess.BrandDao;

import com.yunusAhmet.rentACar.dto.BrandDto;
import com.yunusAhmet.rentACar.dto.CreateBrandRequest;
import com.yunusAhmet.rentACar.entity.Brand;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandManagerTest {

    private BrandDao brandDao;
    private ModelMapper modelMapper;

    private BrandManager brandManager;

    @BeforeEach
    public void setUp() {
        brandDao = mock(BrandDao.class);
        modelMapper = mock(ModelMapper.class);

        brandManager = new BrandManager(brandDao,modelMapper);
    }

    @Test
    public void testCreateBrand_whenBrandNameExists_shouldReturnBrand(){
        CreateBrandRequest request = new CreateBrandRequest("a5");
        Brand brand = new Brand(0,request.getBrandName());

        BrandDto brandDto = new BrandDto(brand.getBrandId(), "a8");

        when(brandDao.save(brand)).thenReturn(brand);

        when(modelMapper.map(brand,BrandDto.class)).thenReturn(brandDto);


        System.out.println(brandManager.createBrand(request).getBrandName() );
        System.out.println(brandManager.createBrand(request).getBrandId() );

        BrandDto result = brandManager.createBrand(request);




        assertEquals(result,brandDto);

//        verify(brandDao).save(brand);
//        verify(modelMapper).map(brand,BrandDto.class);

    }
}