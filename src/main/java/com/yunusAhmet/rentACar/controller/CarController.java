package com.yunusAhmet.rentACar.controller;

import com.yunusAhmet.rentACar.business.CarManager;
import com.yunusAhmet.rentACar.dto.CarDto;
import com.yunusAhmet.rentACar.dto.CreateCarRequest;
import com.yunusAhmet.rentACar.dto.UpdateCarRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(carManager.update(request));
    }


}
