package com.yunusahmet.rentacar.dto.converter;

import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.ColorDto;
import com.yunusahmet.rentacar.entity.Color;

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
