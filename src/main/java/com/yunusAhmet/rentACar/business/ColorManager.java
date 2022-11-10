package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.ColorAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.ColorNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.ColorDao;
import com.yunusAhmet.rentACar.dto.*;
import com.yunusAhmet.rentACar.dto.converter.ColorDtoConverter;
import com.yunusAhmet.rentACar.entity.Color;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorManager {

    private final ColorDao colorDao;
    private final ColorDtoConverter converter;


    public ColorManager(ColorDao colorDao, ColorDtoConverter converter) {
        this.colorDao = colorDao;
        this.converter = converter;

    }
    protected Color getColorByColorId(int colorId){
        return colorDao.findById(colorId).orElseThrow(() -> new ColorNotFoundException(Constant.COLOR_NOT_FOUND));
    }


    public ColorDto createColor(CreateColorRequest request){
        Optional<Color> color =  colorDao.findColorByColorName(request.getColorName());
        if(color.isPresent()){
            throw new ColorAlreadyExistException(Constant.COLOR_ALREADY_EXIST);
        }
        Color color1 = new Color(request.getColorName());
        return converter.convert(colorDao.save(color1));
    }

    public void deleteColorByColorId(int colorId){
        colorDao.deleteById(getColorByColorId(colorId).getColorId());
    }

    public ColorDto updateColor(UpdateColorRequest request){
        Optional<Color> color = colorDao.findColorByColorName(request.getColorName());
        if (color.isPresent()) {
            throw new ColorAlreadyExistException(Constant.COLOR_ALREADY_EXIST);
        }
        Color color1= getColorByColorId(request.getColorId());
        color1.setColorName(request.getColorName());
        return converter.convert(colorDao.save(color1));
    }


}
