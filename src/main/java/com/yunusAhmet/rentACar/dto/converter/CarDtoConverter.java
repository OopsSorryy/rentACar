package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.CarDto;
import com.yunusAhmet.rentACar.entity.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDtoConverter {

    private final ColorDtoConverter colorDtoConverter;
    private final BrandDtoConverter brandDtoConverter;

    public CarDtoConverter(ColorDtoConverter colorDtoConverter, BrandDtoConverter brandDtoConverter) {
        this.colorDtoConverter = colorDtoConverter;
        this.brandDtoConverter = brandDtoConverter;
    }

    public CarDto convert(Car from){
        return  new CarDto(
                from.getCarId(),
                from.getCarName(),
                from.getDailyPrice(),
                from.getProductYear(),
                brandDtoConverter.convert(from.getBrand()),
                from.getCarColors().stream().map(colorDtoConverter::convert).collect(Collectors.toList())
        );
    }

    public List<CarDto> convert(List<Car> fromList){
        return fromList
                .stream()
                .map(from ->new CarDto(
                                from.getCarId(),
                                from.getCarName(),
                                from.getDailyPrice(),
                                from.getProductYear(),
                                brandDtoConverter.convert(from.getBrand()),
                                from.getCarColors().stream().map(colorDtoConverter::convert).toList()
                        )
                ).collect(Collectors.toList());
    }
}
