package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.dataAccess.RentalDao;
import com.yunusAhmet.rentACar.dto.RentACarRequest;
import com.yunusAhmet.rentACar.dto.RentCarDto;
import com.yunusAhmet.rentACar.entity.Car;
import com.yunusAhmet.rentACar.entity.Customer;
import com.yunusAhmet.rentACar.entity.Rental;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RentalManager {

    private final RentalDao rentalDao;

    private final CustomerManager customerManager;

    private final CarManager carManager;
    private final ModelMapper modelMapper;



    public RentalManager(RentalDao rentalDao, CustomerManager customerManager, CarManager carManager, ModelMapper modelMapper) {
        this.rentalDao = rentalDao;
        this.customerManager = customerManager;
        this.carManager = carManager;
        this.modelMapper = modelMapper;
    }

    public RentCarDto rentACar(RentACarRequest request){
        Car car = carManager.findCarByCarId(request.getCarId());
        Customer customer = customerManager.getCustomerByCustomerId(request.getCustomerId());

        Rental rental = new Rental(request.getReturnDate(),customer,car);

        return modelMapper.map(rentalDao.save(rental),RentCarDto.class);

    }



}