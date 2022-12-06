package com.yunusahmet.rentacar.dto.converter;

import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.ImageDto;
import com.yunusahmet.rentacar.entity.Image;

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
