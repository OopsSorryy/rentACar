package com.yunusahmet.rentacar.business;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yunusahmet.rentacar.core.exception.CarNotFoundException;
import com.yunusahmet.rentacar.dataAccess.CarDao;
import com.yunusahmet.rentacar.dto.*;
import com.yunusahmet.rentacar.dto.converter.BrandCarDtoConverter;
import com.yunusahmet.rentacar.dto.converter.CarDtoConverter;
import com.yunusahmet.rentacar.entity.Brand;
import com.yunusahmet.rentacar.entity.Car;
import com.yunusahmet.rentacar.entity.Color;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CarManagerTest {

    private CarDao carDao;
    private BrandManager brandManager;
    private ColorManager colorManager;
    private BrandCarDtoConverter brandCarDtoConverter;

    private CarDtoConverter carDtoConverter;

    private CarManager carManager;
    @BeforeEach
    public void setUp() {
        carDao = mock(CarDao.class);
        brandManager = mock(BrandManager.class);
        colorManager = mock(ColorManager.class);
        carDtoConverter = mock(CarDtoConverter.class);
        brandCarDtoConverter = mock(BrandCarDtoConverter.class);
        carManager = new CarManager(carDao,brandManager,colorManager, brandCarDtoConverter, carDtoConverter);


    }
    @Test
    public void testCreateCar_shouldReturnCarDto(){

        CreateCarRequest request = new CreateCarRequest
                (
                        "bmw",
                        1234L,
                        "2001",
                        1,
                        List.of(1,2)
                );

        Brand brand = new Brand(request.getBrandId(),"a8");
        List<Color> colors = Arrays.asList(new Color(request.getColorId().get(0),"black"),new Color(request.getColorId().get(1),"blue"));
        BrandDto brandDto = new BrandDto(brand.getBrandId(),brand.getBrandName());
        List<ColorDto> colorDtos = Arrays.asList(new ColorDto(1,"black"),new ColorDto(2,"blue"));
        List<Integer> colorIds = List.of(1,2);

        Car car= new Car
                (
                        request.getCarName(),
                        request.getDailyPrice(),
                        request.getProductYear(),
                        brand,
                        colors
                );
        Car saveCar = new Car
                (
                        1,
                        request.getCarName(),
                        request.getDailyPrice(),
                        request.getProductYear(),
                        brand,
                        colors
                );

        CarDto expected = new CarDto
                (
                        1,
                        request.getCarName(),
                        request.getDailyPrice(),
                        request.getProductYear(),
                        brandDto,
                        List.of(new ImageDto()),
                        colorDtos
                );


        when(brandManager.getBrandByBrandId(brand.getBrandId())).thenReturn(brand);

        when(colorManager.getColorsByColorIds(colorIds)).thenReturn(colors);
        when(carDao.save(car)).thenReturn(saveCar);
        when(carDtoConverter.convert(saveCar)).thenReturn(expected);



        CarDto result= carManager.createCar(request);

        assertEquals(expected,result);
        verify(brandManager).getBrandByBrandId(brand.getBrandId());
        verify(colorManager).getColorsByColorIds(colorIds);
        verify(carDao).save(car);
        verify(carDtoConverter).convert(saveCar);
//          BrandDto brandDto=modelMapper.map(brand, BrandDto.class);

    }

    @Test
    public void testDeleteCar_whenCarIdExists_shouldDeleteCar(){
        int brandId =1;

        Brand brand = new Brand(brandId,"a8");
        int carId =1;
       Car car = new Car(carId,"a8",brand);

        when(carDao.findById(1)).thenReturn(Optional.of(car));

        carManager.deleteCarByCarId(car.getCarId());

        verify(carDao).findById(carId);
        verify(carDao).deleteById(car.getCarId());
    }

    @Test
    public void testGetAllCar_whenGiveBrandIdExists_shouldReturnListOfCar(){
        int brandId=2;
        Car car = new Car(1, "bmw",new Brand(2,"a8"));
        Car car1 = new Car(2, "audi",new Brand(2,"a8"));
        BrandCarDto brandCarDto = new BrandCarDto(car.getCarId(), car.getCarName());
        BrandCarDto brandCarDto1 = new BrandCarDto(car1.getCarId(), car1.getCarName());

        List<BrandCarDto> brandCarDtos = Arrays.asList(brandCarDto,brandCarDto1);
        List<Car> cars = Arrays.asList(car,car1);


        when(carDao.getCarsByBrand_BrandId(brandId)).thenReturn(Optional.of(cars));
        when(brandCarDtoConverter.convert(cars)).thenReturn(brandCarDtos);

        List<BrandCarDto> result = carManager.getCarsByBrandId(brandId);
        assertEquals(brandCarDtos,result);

        verify(carDao).getCarsByBrand_BrandId(brandId);
        verify(brandCarDtoConverter).convert(cars);

    }

    @Test
    public void testGetAllCars_shouldReturnCarList(){
        Brand brand = new Brand(1,"a8");
        List<Color> colors = List.of(new Color(1,"black"),
                new Color(2,"blue"));
        List<Car> cars = List.of(new Car(1,"bmw",
                1234L,"2001",brand,colors),new Car(2,"audi",
                2367L,"2020",brand,colors));

        BrandDto brandDto = new BrandDto(1,"a8");
        List<ColorDto> colorsDto = List.of(new ColorDto(1,"black"),
                new ColorDto(2,"blue"));
        List<CarDto> carsDto = List.of(new CarDto(1,"bmw",
                1234L,"2001",brandDto,List.of(new ImageDto()),colorsDto),new CarDto(2,"audi",
                2367L,"2020",brandDto,List.of(new ImageDto()),colorsDto));

        when(carDao.findAll()).thenReturn(cars);
        when(carDtoConverter.convert(cars)).thenReturn(carsDto);
        List<CarDto> result = carManager.getAllCar();
        assertEquals(carsDto,result);

        verify(carDao).findAll();
        verify(carDtoConverter).convert(cars);

    }

    @Test
    public void testUpdateCar_whenCarIdExists_shouldReturnCarDto(){
        Brand brand = new Brand(1,"a8");
        List<Color> colors = List.of(new Color(1,"black"),
                new Color(2,"blue"));

        BrandDto brandDto = new BrandDto(brand.getBrandId(),brand.getBrandName());
        List<ColorDto> colorDtos = Arrays.asList(new ColorDto(1,"black"),new ColorDto(2,"blue"));
        UpdateCarRequest request = new UpdateCarRequest
                (
                        1,
                        "bmw",
                        1234L,
                        "2001",
                        brand.getBrandId(),
                        List.of(colors.get(0).getColorId(),colors.get(1).getColorId())
                );
        Car car = new Car
                (
                        1,
                    "audi",
                    1234L,
                    "2001",
                        brand,
                        List.of(colors.get(0),colors.get(1))
                );
        Car saveCar = new Car
                (
                        1,
                        request.getCarName(),
                        request.getDailyPrice(),
                        request.getProductYear(),
                        brand,
                        colors
                );
        CarDto expected = new CarDto
                (
                        1,
                        request.getCarName(),
                        request.getDailyPrice(),
                        request.getProductYear(),
                        brandDto,
                        List.of(new ImageDto()),
                        colorDtos
                );



        when(colorManager.getColorByColorId(colors.get(0).getColorId())).thenReturn(colors.get(0));
        when(colorManager.getColorByColorId(colors.get(1).getColorId())).thenReturn(colors.get(1));
        when(brandManager.getBrandByBrandId(brand.getBrandId())).thenReturn(brand);
        when(carDao.findById(car.getCarId())).thenReturn(Optional.of(car));
        when(carDao.save(saveCar)).thenReturn(saveCar);
        when(carDtoConverter.convert(car)).thenReturn(expected);

        CarDto result = carManager.update(request);

        assertEquals(expected,result);

        verify(colorManager).getColorByColorId(colors.get(0).getColorId());
        verify(colorManager).getColorByColorId(colors.get(1).getColorId());
        verify(brandManager).getBrandByBrandId(brand.getBrandId());
        verify(carDao).findById(request.getCarId());
        verify(carDtoConverter).convert(car);
        verify(carDao).save(car);

    }

    @Test
    public void testDeleteAndUpdateCar_whenCarIdDoesntExist_shouldReturnException(){

        when(carDao.findById(1)).thenReturn(Optional.empty());
        assertThrows(CarNotFoundException.class,()-> carManager.findCarByCarId(1));
    }

}