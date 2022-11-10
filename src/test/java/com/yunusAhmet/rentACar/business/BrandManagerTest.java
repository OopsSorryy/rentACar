package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.BrandAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.BrandNotFoundException;
import com.yunusAhmet.rentACar.dataAccess.BrandDao;
import com.yunusAhmet.rentACar.dto.*;
import com.yunusAhmet.rentACar.dto.converter.BrandCarDtoConverter;
import com.yunusAhmet.rentACar.dto.converter.BrandDtoConverter;
import com.yunusAhmet.rentACar.entity.Brand;
import com.yunusAhmet.rentACar.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandManagerTest {

    private BrandDao brandDao;
    private BrandCarDtoConverter brandCarDtoConverter;
    private BrandDtoConverter brandDtoConverter;
    private BrandManager brandManager;
    @BeforeEach
    void setUp() {
        brandDao = mock(BrandDao.class);
        brandDtoConverter = mock(BrandDtoConverter.class);
        brandCarDtoConverter = mock(BrandCarDtoConverter.class);
        brandManager = new BrandManager(brandDao,brandDtoConverter, brandCarDtoConverter);
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
    public void testGetAllCar_whenGiveBrandIdExists_shouldReturnListOfCar(){
        int brandId=2;
        Car car = new Car(1, "bmw",new Brand(2,"a8"));
        Car car1 = new Car(2, "audi",new Brand(2,"a8"));
        BrandCarDto brandCarDto = new BrandCarDto(car.getCarId(), car.getCarName());
        BrandCarDto brandCarDto1 = new BrandCarDto(car1.getCarId(), car1.getCarName());

        List<BrandCarDto> brandCarDtos = Arrays.asList(brandCarDto,brandCarDto1);

        Brand brand= new Brand(2,"a8",Arrays.asList(car,car1));
        List<Car> cars = Arrays.asList(car,car1);


        when(brandDao.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandCarDtoConverter.convert(cars)).thenReturn(brandCarDtos);

        List<BrandCarDto> result = brandManager.getAllCarByBrandId(brandId);
        assertEquals(brandCarDtos,result);

        verify(brandDao).findById(brandId);
        verify(brandCarDtoConverter).convert(cars);

    }

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