package com.yunusahmet.rentacar.business;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yunusahmet.rentacar.core.constant.Constant;
import com.yunusahmet.rentacar.core.exception.CarHasNotImageException;
import com.yunusahmet.rentacar.core.exception.CarNotFoundException;
import com.yunusahmet.rentacar.core.exception.ImageNotFoundException;
import com.yunusahmet.rentacar.core.exception.MaxImageException;
import com.yunusahmet.rentacar.dataAccess.ImageDao;
import com.yunusahmet.rentacar.dto.ImageDto;
import com.yunusahmet.rentacar.dto.converter.ImageConverter;
import com.yunusahmet.rentacar.entity.Car;
import com.yunusahmet.rentacar.entity.Image;

import java.util.List;
import java.util.Map;



@Service
public class ImageManager  {

    private final ImageDao imageDao;

    private final CarManager carManager;

    private final ImageConverter imageConverter;
    private final CloudStorageManager cloudStorageManager;



    public ImageManager
            (
                    ImageDao imageDao ,
             CarManager carManager,
             CloudStorageManager cloudStorageManager,
             ImageConverter imageConverter
            )
    {

        this.imageDao = imageDao;
        this.carManager = carManager;
        this.cloudStorageManager = cloudStorageManager;

        this.imageConverter = imageConverter;
    }

    protected Image getImageByImageId(int imageId){
        return imageDao.findById(imageId).orElseThrow(() -> new ImageNotFoundException(Constant.IMAGE_NOT_FOUND));
    }

    protected void maxImageControl(int carId){
        List<Image> images =
                imageDao.getImagesByCar_CarId(carId).orElseThrow(
                        () -> new CarNotFoundException(Constant.CAR_NOT_FOUND));
        if(images.size()==6){
            throw new MaxImageException(Constant.MAX_IMAGE_LESS_THAN_SIX);
        }
        if(images.size()==0){
            throw new CarHasNotImageException(Constant.CAR_HAS_NOT_IMAGE);
        }
    }

    public ImageDto addImage(MultipartFile multipartFile, int carId) {
        maxImageControl(carId);
        Map<?,?> upload = cloudStorageManager.upload(multipartFile);
        Car car =carManager.findCarByCarId(carId);
        Image image = new Image(upload.get("url").toString(),car);
        return imageConverter.converter(imageDao.save(image));
    }

    public void deleteImageByImageId(int imageId){
        imageDao.deleteById(getImageByImageId(imageId).getId());
    }

}