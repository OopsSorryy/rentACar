package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.ColorDto;
import com.yunusAhmet.rentACar.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorDtoConverter {

    public ColorDto convert(Color from){

        return new ColorDto
                (
                        from.getColorId(),
                        from.getColorName()
                );
    }
}
