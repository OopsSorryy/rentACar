package com.yunusAhmet.rentACar.controller;

import com.yunusAhmet.rentACar.business.BrandManager;
import com.yunusAhmet.rentACar.dto.BrandCarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/brand")
@RestController
public class BrandController {

    private final BrandManager brandManager;

    public BrandController(BrandManager brandManager) {
        this.brandManager = brandManager;
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<List<BrandCarDto>> getAllCarByBrandId(@PathVariable int brandId){
        return new ResponseEntity<>(brandManager.getAllCarByBrandId(brandId), HttpStatus.OK);
    }
}
