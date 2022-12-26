package com.yunusahmet.rentacar.business;


import org.springframework.stereotype.Service;

import com.yunusahmet.rentacar.core.constant.Constant;
import com.yunusahmet.rentacar.core.exception.ColorAlreadyExistException;
import com.yunusahmet.rentacar.core.exception.ColorNotFoundException;
import com.yunusahmet.rentacar.dataAccess.ColorDao;
import com.yunusahmet.rentacar.dto.*;
import com.yunusahmet.rentacar.dto.converter.ColorDtoConverter;
import com.yunusahmet.rentacar.entity.Color;

import java.util.List;
import java.util.Optional;

@Service
public class ColorManager {

    private final ColorDao colorDao;
    private final ColorDtoConverter converter;


    public ColorManager(ColorDao colorDao, ColorDtoConverter converter) {
        this.colorDao = colorDao;
        this.converter = converter;

    }
    public Color getColorByColorId(int colorId){
        return colorDao.findById(colorId).orElseThrow(() -> new ColorNotFoundException(Constant.COLOR_NOT_FOUND));
    }

    public List<Color> getColorsByColorIds(List<Integer> colorIds){
        return colorDao.findColorsByColorIdIn(colorIds);
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
