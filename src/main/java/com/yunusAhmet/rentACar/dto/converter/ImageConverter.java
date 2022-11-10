package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.ImageDto;
import com.yunusAhmet.rentACar.entity.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageConverter {

    public ImageDto converter(Image from){
        return new ImageDto(from.getId(),from.getUrl());
    }

    public List<ImageDto> converter(List<Image> fromList){
        return fromList.stream().map(image -> new ImageDto(image.getId(), image.getUrl())).collect(Collectors.toList());
    }
}
