package com.yunusahmet.rentacar.business;

import org.springframework.stereotype.Service;

import com.yunusahmet.rentacar.core.constant.Constant;
import com.yunusahmet.rentacar.core.exception.BrandNotFoundException;
import com.yunusahmet.rentacar.core.exception.CarNotFoundException;
import com.yunusahmet.rentacar.dataAccess.CarDao;
import com.yunusahmet.rentacar.dto.BrandCarDto;
import com.yunusahmet.rentacar.dto.CarDto;
import com.yunusahmet.rentacar.dto.CreateCarRequest;
import com.yunusahmet.rentacar.dto.UpdateCarRequest;
import com.yunusahmet.rentacar.dto.converter.BrandCarDtoConverter;
import com.yunusahmet.rentacar.dto.converter.CarDtoConverter;
import com.yunusahmet.rentacar.entity.Brand;
import com.yunusahmet.rentacar.entity.Car;
import com.yunusahmet.rentacar.entity.Color;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class CarManager {

    private final CarDao carDao;
    private final BrandManager brandManager;
    private final ColorManager colorManager;

    private final BrandCarDtoConverter brandCarDtoConverter;
    private final CarDtoConverter carDtoConverter;


    public CarManager(
            CarDao carDao,
            BrandManager brandManager,
            ColorManager colorManager,
            BrandCarDtoConverter brandCarDtoConverter, CarDtoConverter carDtoConverter) {

        this.carDao = carDao;
        this.brandManager = brandManager;
        this.colorManager = colorManager;
        this.brandCarDtoConverter = brandCarDtoConverter;
        this.carDtoConverter = carDtoConverter;

    }

    public CarDto createCar(CreateCarRequest createCarRequest){
        Brand brand = brandManager.getBrandByBrandId(createCarRequest.getBrandId());
        List<Integer> colorId=createCarRequest.getColorId();
        List<Color> colors = colorManager.getColorsByColorIds(colorId);
        Car car = new Car
                (
                        createCarRequest.getCarName(),
                        createCarRequest.getDailyPrice(),
                        createCarRequest.getProductYear(),
                        brand,
                        colors);
        return carDtoConverter.convert(carDao.save(car));
    }

    public List<CarDto> getAllCar() {
        List<Car> cars = this.carDao.findAll();
       return carDtoConverter.convert(cars);

    }


    public Car findCarByCarId(int carId){
        return carDao.findById(carId).orElseThrow(() -> new CarNotFoundException(Constant.CAR_NOT_FOUND));
    }

    public void deleteCarByCarId(int carId){
        carDao.deleteById(findCarByCarId(carId).getCarId());
    }

    private List<Car> findCarsByBrandId(int brandId){
        return carDao.getCarsByBrand_BrandId(brandId).orElseThrow(()->new BrandNotFoundException(Constant.BRAND_NOT_FOUND));
    }

    public List<BrandCarDto> getCarsByBrandId(int brandId){
        return brandCarDtoConverter.convert(findCarsByBrandId(brandId));
    }

    public CarDto update(UpdateCarRequest updateCarRequest) {
        Car car = findCarByCarId(updateCarRequest.getCarId());
        List<Integer> colorId = updateCarRequest.getColorId();
        List<Color> color = colorId.stream().map(colorManager::getColorByColorId).collect(Collectors.toList());
        car.setCarColors(color);
        car.setCarName(updateCarRequest.getCarName());
        int brandId = updateCarRequest.getBrandId();
        Brand brand = brandManager.getBrandByBrandId(brandId);
        car.setBrand(brand);
        car.setDailyPrice(updateCarRequest.getDailyPrice());
        car.setProductYear(updateCarRequest.getProductYear());
       return carDtoConverter.convert(carDao.save(car));

    }
}
