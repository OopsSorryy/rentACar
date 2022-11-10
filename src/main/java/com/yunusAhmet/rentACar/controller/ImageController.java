package com.yunusAhmet.rentACar.controller;


import com.yunusAhmet.rentACar.business.ImageManager;
import com.yunusAhmet.rentACar.dto.ImageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                                @Valid @RequestParam("carId") int carId) throws Exception
    {

        return  new ResponseEntity<>(imageManager.addImage(multipartFile,carId),HttpStatus.CREATED);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImageByImageId(@PathVariable int imageId){
        imageManager.deleteImageByImageId(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
