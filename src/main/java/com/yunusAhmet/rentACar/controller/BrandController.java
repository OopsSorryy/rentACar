package com.yunusAhmet.rentACar.controller;

import com.yunusAhmet.rentACar.business.BrandManager;
import com.yunusAhmet.rentACar.dto.BrandCarDto;
import com.yunusAhmet.rentACar.dto.BrandDto;
import com.yunusAhmet.rentACar.dto.CreateBrandRequest;
import com.yunusAhmet.rentACar.dto.UpdateBrandRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody CreateBrandRequest request){
        return new ResponseEntity<>(brandManager.createBrand(request),HttpStatus.CREATED);
    }
    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrandByBrandId(@PathVariable int brandId){
        brandManager.deleteBrandByBrandId(brandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BrandDto> updateBrand(@Valid @RequestBody UpdateBrandRequest request){
        return ResponseEntity.ok(brandManager.updateBrand(request));
    }
}
