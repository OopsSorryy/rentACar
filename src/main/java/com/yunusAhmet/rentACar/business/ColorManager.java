package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.ColorAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.ColorNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.ColorDao;
import com.yunusAhmet.rentACar.dto.ColorDto;
import com.yunusAhmet.rentACar.dto.CreateColorRequest;
import com.yunusAhmet.rentACar.dto.UpdateColorRequest;
import com.yunusAhmet.rentACar.entity.Color;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorManager {

    private final ColorDao colorDao;
    private final ModelMapper modelMapper;


    public ColorManager(ColorDao colorDao, ModelMapper modelMapper) {
        this.colorDao = colorDao;
        this.modelMapper = modelMapper;
    }
    protected Color getColorByColorId(int colorId){
        return colorDao.findById(colorId).orElseThrow(() -> new ColorNotFoundException(Constant.COLOR_NOT_FOUND));
    }

    protected void findTheSameColor(CreateColorRequest request){
        Optional<Color> color =  colorDao.findColorByColorName(request.getColorName());
        if(color.isPresent()){
            throw new ColorAlreadyExistException(Constant.COLOR_ALREADY_EXIST);
        }

    }

    public ColorDto createColor(CreateColorRequest request){
        findTheSameColor(request);
        Color color = new Color(request.getColorName());
        return modelMapper.map(colorDao.save(color),ColorDto.class);
    }

    public void deleteColorByColorId(int colorId){
        colorDao.deleteById(getColorByColorId(colorId).getColorId());
    }

    public ColorDto updateColor(UpdateColorRequest request){
        Color color = getColorByColorId(request.getColorId());
        color.setColorName(request.getColorName());
        return modelMapper.map(colorDao.save(color),ColorDto.class);
    }


}