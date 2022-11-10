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

    private final ImageConverter imageConverter;

    public CarDtoConverter(ColorDtoConverter colorDtoConverter, BrandDtoConverter brandDtoConverter, ImageConverter imageConverter) {
        this.colorDtoConverter = colorDtoConverter;
        this.brandDtoConverter = brandDtoConverter;
        this.imageConverter = imageConverter;
    }

    public CarDto convert(Car from){
        return  new CarDto(
                from.getCarId(),
                from.getCarName(),
                from.getDailyPrice(),
                from.getProductYear(),
                brandDtoConverter.convert(from.getBrand()),
                imageConverter.converter(from.getImages()),
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
                        imageConverter.converter(from.getImages()),
                        from.getCarColors().stream().map(colorDtoConverter::convert).toList()
                        )
                ).collect(Collectors.toList());
    }
}
