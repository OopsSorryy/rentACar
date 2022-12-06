package com.yunusahmet.rentacar.business;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CloudStorageManager {

    private final Cloudinary cloudinary;

    public CloudStorageManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public Map<?,?> upload(MultipartFile multipartFile) {

        try {
          Map<?,?> upload= cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            return upload;
        } catch (IOException e) {
            return null;
        }
    }


    public String delete(String publicIdOfImage) {

        List<String> publicIdsOfImages = new ArrayList<String>();
        publicIdsOfImages.add(publicIdOfImage);

        try {
             cloudinary.api().deleteResources(publicIdsOfImages, ObjectUtils.emptyMap());
            return "Deleted";
        } catch (Throwable e) {
            e.printStackTrace();
            return "Created Error";
        }
    }

}
