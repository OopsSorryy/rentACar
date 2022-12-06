package com.yunusahmet.rentacar.dto.converter;

import com.yunusahmet.rentacar.dto.ColorDto;
import com.yunusahmet.rentacar.entity.Color;
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
