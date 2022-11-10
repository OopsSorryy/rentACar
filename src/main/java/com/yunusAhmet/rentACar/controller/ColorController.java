package com.yunusAhmet.rentACar.controller;

import com.yunusAhmet.rentACar.business.ColorManager;
import com.yunusAhmet.rentACar.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RequestMapping("/color")
@RestController
public class ColorController {

    private final ColorManager colorManager;

    public ColorController(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    @PostMapping()
    public ResponseEntity<ColorDto> createColor(@Valid @RequestBody CreateColorRequest request){
        return new ResponseEntity<>(colorManager.createColor(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{colorId}")
    public ResponseEntity<Void> deleteColorByColorId(@PathVariable int colorId){
        colorManager.deleteColorByColorId(colorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ColorDto> updateColor(@Valid @RequestBody UpdateColorRequest request){
        return ResponseEntity.ok(colorManager.updateColor(request));
    }

}
