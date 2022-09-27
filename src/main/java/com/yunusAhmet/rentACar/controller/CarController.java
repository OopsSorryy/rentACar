package com.yunusAhmet.rentACar.controller;

import com.yunusAhmet.rentACar.business.CarManager;
import com.yunusAhmet.rentACar.dto.CarDto;
import com.yunusAhmet.rentACar.dto.CreateCarRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/car")
@RestController
public class CarController {

    private final CarManager carManager;

    public CarController(CarManager carManager) {
        this.carManager = carManager;
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CreateCarRequest request){

        return new ResponseEntity<>(carManager.createCar(request),HttpStatus.CREATED);
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

}
