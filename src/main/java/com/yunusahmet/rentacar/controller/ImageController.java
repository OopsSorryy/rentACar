package com.yunusahmet.rentacar.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yunusahmet.rentacar.business.ImageManager;
import com.yunusahmet.rentacar.dto.ImageDto;

import javax.validation.Valid;

@RequestMapping("/image")
@RestController
public class ImageController {

    private final ImageManager imageManager;


    public ImageController(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    @PostMapping()
    public ResponseEntity<ImageDto> createImage(@Valid @RequestParam("file") MultipartFile multipartFile,
                                                @Valid @RequestParam("carId") int carId)
    {

        return  new ResponseEntity<>(imageManager.addImage(multipartFile,carId),HttpStatus.CREATED);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImageByImageId(@PathVariable int imageId){
        imageManager.deleteImageByImageId(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
