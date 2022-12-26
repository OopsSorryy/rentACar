package com.yunusahmet.rentacar.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yunusahmet.rentacar.business.CarManager;
import com.yunusahmet.rentacar.dto.BrandCarDto;
import com.yunusahmet.rentacar.dto.CarDto;
import com.yunusahmet.rentacar.dto.CreateCarRequest;
import com.yunusahmet.rentacar.dto.UpdateCarRequest;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/car")
@RestController

public class CarController {

    private final CarManager carManager;

    public CarController(CarManager carManager) {
        this.carManager = carManager;
    }

    @PostMapping()
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CreateCarRequest request){
        return new ResponseEntity<>(carManager.createCar(request),HttpStatus.CREATED);
    }
    @GetMapping("/{brandId}")
    public ResponseEntity<List<BrandCarDto>> getAllCarByBrandId(@PathVariable int brandId){
        return ResponseEntity.ok(carManager.getCarsByBrandId(brandId));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCar(){
        return ResponseEntity.ok(carManager.getAllCar());
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCarByCarId(@PathVariable int carId){
        carManager.deleteCarByCarId(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@Valid @RequestBody UpdateCarRequest request){
        return new ResponseEntity<>(carManager.update(request),HttpStatus.CREATED);
    }


}
