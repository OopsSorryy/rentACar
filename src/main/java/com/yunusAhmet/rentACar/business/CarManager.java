package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.CarNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.CarDao;
import com.yunusAhmet.rentACar.dto.CarDto;
import com.yunusAhmet.rentACar.dto.CreateCarRequest;
import com.yunusAhmet.rentACar.entity.Brand;
import com.yunusAhmet.rentACar.entity.Car;
import com.yunusAhmet.rentACar.entity.Color;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager {

    private final CarDao carDao;
    private final BrandManager brandManager;
    private final ColorManager colorManager;

    private final ModelMapper modelMapper;


    public CarManager(CarDao carDao, BrandManager brandManager, ColorManager colorManager, ModelMapper modelMapper) {
        this.carDao = carDao;
        this.brandManager = brandManager;
        this.colorManager = colorManager;
        this.modelMapper = modelMapper;
    }

    public CarDto addCar(CreateCarRequest createCarRequest){
        Brand brand = brandManager.getBrandByBrandId(createCarRequest.getBrandId());

        List<Integer> colorId=createCarRequest.getColorId();
        List<Color> colors = colorId.stream().map(colorManager::getColorByColorId).collect(Collectors.toList());

        Car car = new Car( createCarRequest.getCarName(),createCarRequest.getDailyPrice(),createCarRequest.getProductYear(),brand,colors);

        return modelMapper.map(carDao.save(car),CarDto.class);

    }

    public List<CarDto> getAllCar() {
        List<Car> cars = this.carDao.findAll();
        List<CarDto> dtos= cars.stream().map(car -> modelMapper.map(car,CarDto.class)).collect(Collectors.toList());
        return dtos;
    }

    protected Car findCarByCarId(int carId){
        return carDao.findById(carId).orElseThrow(() -> new CarNotFoundException(Constant.CAR_NOT_FOUND));
    }

    public void deleteCarByCarId(int carId){
           if(carDao.findById(carId).isPresent()){
               carDao.deleteById(carId);
           }else{
               throw new CarNotFoundException(Constant.CAR_NOT_FOUND);
           }

    }
}
