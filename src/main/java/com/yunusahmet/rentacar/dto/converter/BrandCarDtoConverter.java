package com.yunusahmet.rentacar.dto.converter;

import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.BrandCarDto;
import com.yunusahmet.rentacar.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandCarDtoConverter {

    public final ColorDtoConverter colorDtoConverter;

    public BrandCarDtoConverter(ColorDtoConverter colorDtoConverter) {
        this.colorDtoConverter = colorDtoConverter;
    }

    public BrandCarDto convert(Car from){
        return new BrandCarDto
                (
                        from.getCarId(),
                        from.getCarName(),
                        from.getDailyPrice(),
                        from.getProductYear(),
                        from.getCarColors().stream().map(colorDtoConverter::convert).toList()
                );
    }

    public List<BrandCarDto> convert(List<Car> fromList){
        return fromList
                .stream()
                .map(from ->new BrandCarDto(
                from.getCarId(),
                from.getCarName(),
                from.getDailyPrice(),
                from.getProductYear(),
                from.getCarColors().stream().map(colorDtoConverter::convert).toList())
                ).collect(Collectors.toList());
    }
}
