package com.yunusahmet.rentacar.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.yunusahmet.rentacar.core.exception.BrandAlreadyExistException;
import com.yunusahmet.rentacar.core.exception.BrandNotFoundException;
import com.yunusahmet.rentacar.dataAccess.BrandDao;
import com.yunusahmet.rentacar.dto.*;
import com.yunusahmet.rentacar.dto.converter.BrandDtoConverter;
import com.yunusahmet.rentacar.entity.Brand;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandManagerTest {

    private BrandDao brandDao;
    private BrandDtoConverter brandDtoConverter;
    private BrandManager brandManager;


    @BeforeEach
    void setUp() {
        brandDao = mock(BrandDao.class);
        brandDtoConverter = mock(BrandDtoConverter.class);
        brandManager = new BrandManager(brandDao, brandDtoConverter);
    }
            @Test
            public void testCreateBrand_whenMustNotFindSameBrandInDataBase_shouldReturnBrandDto(){

            CreateBrandRequest request =new CreateBrandRequest("a9");

            Brand brand= new Brand(request.getBrandName());
            Brand saveBrand = new Brand(1,request.getBrandName());
            BrandDto brandDto = new BrandDto(1,request.getBrandName());



            when(brandDao.save(brand)).thenReturn(saveBrand);
            when(brandDtoConverter.convert(saveBrand)).thenReturn(brandDto);


           BrandDto result= brandManager.createBrand(request);

            assertEquals(brandDto,result);
            verify(brandDao).save(brand);
            verify(brandDtoConverter).convert(saveBrand);
//          BrandDto brandDto=modelMapper.map(brand, BrandDto.class);

    }

    @Test
    public void testCreateBrand_whenBrandNameAlreadyExists_shouldReturnException() {
        CreateBrandRequest request = new CreateBrandRequest("a8");
        Brand brand= new Brand(1,"a8");
        when(brandDao.findBrandByBrandName(brand.getBrandName())).
                thenReturn(Optional.of(brand));

       assertThrows( BrandAlreadyExistException.class,() -> brandManager.createBrand(request) );
        verify(brandDao).findBrandByBrandName(request.getBrandName());


    }
    @Test
    public void testDeleteAndUpdateBrand_whenBrandIdDoesntExist_shouldReturnException(){

        when(brandDao.findById(1)).thenReturn(Optional.empty());
        assertThrows(BrandNotFoundException.class,()-> brandManager.getBrandByBrandId(1));
    }

    @Test
    public void testUpdateBrand_whenBrandIdExists_shouldReturnBrandDto(){
        UpdateBrandRequest request = new UpdateBrandRequest(1,"a1");
        Brand brand = new Brand(1,"a2");
        BrandDto brandDto = new BrandDto(1,"a2");
        when(brandDao.findBrandByBrandName(brand.getBrandName())).thenReturn(Optional.of(brand));

        when(brandDao.findById(1)).thenReturn(Optional.of(brand));
        when(brandDao.save(brand)).thenReturn(brand);
        when(brandDtoConverter.convert(brand)).thenReturn(brandDto);

        BrandDto result = brandManager.updateBrand(request);

        assertEquals(brandDto,result);
    }

    @Test
    public void testUpdateBrand_whenBrandNameAlreadyExists_shouldReturnException() {
        UpdateBrandRequest request = new UpdateBrandRequest(0,"a8");
        Brand brand= new Brand(0,"a8");
        when(brandDao.findBrandByBrandName(brand.getBrandName())).
                thenReturn(Optional.of(brand));

        assertThrows( BrandAlreadyExistException.class,() -> brandManager.updateBrand(request));

        verify(brandDao).findBrandByBrandName(request.getBrandName());}


    @Test
    public void testDeleteBrand_whenBrandIdExists_shouldDeleteBrand(){
      int brandId =1;

        Brand brand = new Brand(brandId,"a8");

        when(brandDao.findById(1)).thenReturn(Optional.of(brand));

        brandManager.deleteBrandByBrandId(brand.getBrandId());

        verify(brandDao).findById(brandId);
        verify(brandDao).deleteById(brand.getBrandId());
    }


}