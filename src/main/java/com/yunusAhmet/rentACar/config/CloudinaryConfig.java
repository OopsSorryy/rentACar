package com.yunusAhmet.rentACar.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.yunusAhmet.rentACar.business.CloudStorageManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
   public Cloudinary cloudinaryAccount(){
        return  new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnw3wdetw",
                "api_key", "163228362673845",
                "api_secret", "94yl1rWhrwMcPs0WV9CG5Df9kBw",
                "secure", true));
    }

    @Bean
    public CloudStorageManager cloudStorageService() {
        return new CloudStorageManager(cloudinaryAccount());
    }
}
